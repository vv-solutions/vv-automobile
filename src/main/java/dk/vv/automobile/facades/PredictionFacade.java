package dk.vv.automobile.facades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class PredictionFacade {


    public BigDecimal getPricePrediction(String imagePath,String numberplate, Integer km) throws IOException {

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
        return new BigDecimal(pred);
    }
}
