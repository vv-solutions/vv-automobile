package dk.vv.automobile.facades;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.vv.automobile.dtos.PredictionDTO;
import dk.vv.automobile.errorhandling.PredictionException;
import org.jboss.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class PredictionFacade {



    private final Logger logger;

    private final ObjectMapper mapper;

    public PredictionFacade(Logger logger) {
        this.logger = logger;
        mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    public PredictionDTO getPricePrediction(String imagePath,String numberplate, Integer km) throws IOException, PredictionException {

        String numberplateArg = "";

        if(numberplate != null && !numberplate.isEmpty()){
            numberplateArg = "--numberplate="+numberplate+ " ";
        }

        String kmArg = "";

        if(km != null){
            kmArg = "--km="+km+" ";
        }

        String imgArg = "";

        if(imagePath != null && !imagePath.isEmpty()){
            imgArg = "--image="+imagePath+" ";
        }

        Process p = Runtime.getRuntime().exec(String.format("python src/main/resources/python/main.py %s%s%s", kmArg, numberplateArg,imgArg));

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

        String pred = null;
        String line;
        while ((line = stdInput.readLine()) != null) {
            pred = line;
        }

        if(pred.contains("error:")){
            logger.errorf("prediction failed with message: [%s]",pred.split("error:")[1]);
            throw new PredictionException(pred.split("error:")[1]);
        }

        return mapper.readValue(pred, PredictionDTO.class);
    }
}
