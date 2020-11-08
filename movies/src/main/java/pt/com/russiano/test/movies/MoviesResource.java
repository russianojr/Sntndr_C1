package pt.com.russiano.test.movies;


import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.sl.usermodel.LineDecoration;
import org.apache.poi.xslf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pt.com.russiano.test.movies.model.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/movies")
public class MoviesResource {
    // -------------------------- OTHER METHODS --------------------------
    public final String URL_THEMOVIEDB_GET_ACTOR = "https://api.themoviedb.org/3/search/person?api_key={api_key}&language={language}&query={query}&page={page}&include_adult={include_adult}";//"https://api.themoviedb.org/3/search/person?";
    public final String URL_THEMOVIEDB_GET_CREDITS = "https://api.themoviedb.org/3/movie/{id}/credits?api_key={api_key}";
    public final String THEMOVIEDB_LANGUAGE = "en-US";
    public final Boolean THEMOVIEDB_INCLUDE_ADULT = false;
    public final String URL_NYTIMES = "https://api.nytimes.com/svc/movies/v2/reviews/search.json?query={query}&api-key={api-key}&offset={offset}";

    @Value("${nytimes_api_key:}")
    private String nytimes_api_key;

    @Value("${themoviedb_api_key:}")
    private String themoviedb_api_key;

    @CrossOrigin(origins = "*")
    @GetMapping("/busca/{actor}")
    public List<List<MoviesCommand>> getMovie(@PathVariable String actor) {
        return Collections.singletonList(getMoviesApi(actor));
    }

    public List<MoviesCommand> getMoviesApi(String actor) {
        List<MoviesCommand> nyt = getMoviesNytimes(actor);
       List<MoviesCommand> themoviedb = getMoviesThemoviedb(actor);

        for (MoviesCommand obj : nyt) {
            if (themoviedb.stream().noneMatch(x -> StringUtils.endsWithIgnoreCase(x.getNomeFilme(),obj.getNomeFilme())))
                themoviedb.add(obj);
        }
        return themoviedb;
    }

    public List<MoviesCommand> getMoviesNytimes(String query) {

        List<MoviesCommand> retorno = new ArrayList<>();
        int page = 0;
        int totalPages = 1;
        RestTemplate restTemplate = new RestTemplate();
        NytimesCommand nytimes = null;
        Map<String, String> params;
        boolean has_more = true;
        List<NytimesResultsCommand> nytimesResults = new ArrayList<>();

        while (has_more) {
            params = new HashMap<String, String>();
            params.put("api-key", nytimes_api_key);
            params.put("query", query);
            params.put("offset", String.valueOf(page));

            nytimes = restTemplate
                    .getForObject(URL_NYTIMES, NytimesCommand.class, params);

            has_more = nytimes.isHas_more() == true ? true : false;
            if (has_more)
                page += 20;

            nytimesResults = Stream.concat(nytimesResults.stream(), (nytimes.getResults()).stream())
                    .collect(Collectors.toList());

        }

        MoviesCommand movie = null;
        for (NytimesResultsCommand results :
                nytimesResults) {
            movie = new MoviesCommand();
            movie.setAnoLancamento(results.getOpening_date());
            movie.setNome(query);
            movie.setNomeFilme(results.getDisplay_title());
            retorno.add(movie);
        }

        return retorno;
    }


    public List<MoviesCommand> getMoviesThemoviedb(String query) {

        List<MoviesCommand> retorno = new ArrayList<>();
        int page = 1;
        int totalPages = 1;
        RestTemplate restTemplate = new RestTemplate();
        TheMovieDbCommand themoviedb;

        while (page <= totalPages) {

            Map<String, String> params = new HashMap<String, String>();
            params.put("api_key", themoviedb_api_key);
            params.put("language", THEMOVIEDB_LANGUAGE);
            params.put("include_adult", THEMOVIEDB_INCLUDE_ADULT.toString());
            params.put("query", query);
            params.put("page", String.valueOf(page));

            themoviedb = restTemplate
                    .getForObject(URL_THEMOVIEDB_GET_ACTOR, TheMovieDbCommand.class, params);


            MoviesCommand moviesCommand = null;

            String name = "";
            MoviesCommand movieCredit = new MoviesCommand();
            for (TheMovieDbResultCommand results : themoviedb.getResults()) {
                name = results.getName();
                for (TheMovieDbKnownforCommand known_for : results.getKnown_for()) {
                    moviesCommand = new MoviesCommand();
                    moviesCommand.setNome(name);
                    moviesCommand.setAnoLancamento(known_for.getRelease_date());
                    moviesCommand.setNomeFilme(known_for.getTitle());

                    movieCredit = getMoviesCredtsThemoviedb(known_for.getId());
                    moviesCommand.setProtagonista(movieCredit.getProtagonista());
                    moviesCommand.setRealizador(movieCredit.getRealizador());
                    retorno.add(moviesCommand);
                }
            }
            totalPages = themoviedb.getTotal_pages();
            page++;
        }

        return retorno;
    }


    public MoviesCommand getMoviesCredtsThemoviedb(int id) {
        MoviesCommand retorno = new MoviesCommand();
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", themoviedb_api_key);
        params.put("id", String.valueOf(id));
        TheMovieDbCreditsCommand credits;
        credits = restTemplate
                .getForObject(URL_THEMOVIEDB_GET_CREDITS, TheMovieDbCreditsCommand.class, params);

        String protagonista = "";
        String diretor = "";
        int count_actor = 0; //considero apenas os 5 primeiros atores principais protagonistas
        for (TheMovieDbCreditsCastCommand cast : credits.getCast()) {
            if (cast.getCharacter() != null && !cast.getCharacter().isEmpty()) {
                protagonista += cast.getCharacter() + ";";
                count_actor++;
            }
            if (count_actor >= 5)
                break;
        }
        for (TheMovieDbCreditsCrewCommand crew : credits.getCrew()) {
            if (crew.getJob().equalsIgnoreCase("Director")) {
                diretor += crew.getName() + ";";
            }
        }

        retorno.setProtagonista(protagonista);
        retorno.setRealizador(diretor);

        return retorno;
    }


    @GetMapping(
            value = "/getFile/{actor}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    @ResponseBody
    public HttpEntity<byte[]> getFile(HttpServletResponse response, @PathVariable String actor) throws IOException {

        List<MoviesCommand> dados = getMoviesApi(actor);


        XMLSlideShow ppt = new XMLSlideShow();
        XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
        XSLFSlideLayout titleLayout = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);

        int qtdSlides = (int) Math.ceil(dados.stream().count() / 9.00);
        for (int i = 0; i < qtdSlides; i++)
            ppt.createSlide(titleLayout);

        int indexDados = 0;
        for (int i = 0; i < qtdSlides; i++) {

            XSLFSlide slide1 = (ppt.getSlides()).get(i);
            XSLFTextShape title1 = slide1.getPlaceholder(0);
            title1.setText("Busca pelo nome:" + actor);


            XSLFTextShape body1 = slide1.getPlaceholder(1);
            body1.clearText();

            body1.addNewTextParagraph().addLineBreak();
            body1.setLineHeadDecoration(LineDecoration.DecorationShape.NONE);
            XSLFTable table = slide1.createTable(10, 5);

            table.setAnchor(new Rectangle2D.Double(50, 100, 500, 20));

            table.getCell(0, 0).setText("Nome");
            table.getCell(0, 1).setText("Nome Filme");
            table.getCell(0, 2).setText("Realizador");
            table.getCell(0, 3).setLineWidth(250.00);
            table.getCell(0, 3).setText("Protagonista");
            table.getCell(0, 4).setText("Ano Lançamento");
            for (int row = 1; row < 10; row++) {
                table.getCell(row, 0).setText(dados.get(indexDados).getNome());
                table.getCell(row, 1).setText(dados.get(indexDados).getNomeFilme());
                table.getCell(row, 2).setText(dados.get(indexDados).getRealizador());
                table.getCell(row, 3).setText(dados.get(indexDados).getProtagonista());
                table.getCell(row, 3).setLineWidth(250.00);
                table.getCell(row, 4).setText(dados.get(indexDados).getAnoLancamento());
                indexDados++;
                if(indexDados >= dados.stream().count())
                    break;
            }

        }


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ppt.write(byteArrayOutputStream);
        ppt.close();
        InputStream in = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        ContentDisposition contentDisposition = ContentDisposition.builder("inline")
                .filename(actor + ".pptx")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);

        return new HttpEntity<>(IOUtils.toByteArray(in), headers);
    }

}
