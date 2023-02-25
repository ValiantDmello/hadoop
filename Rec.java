import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;

public class Rec {
    public static String dataFile = "home/";
    
    public static void main(String args[]) throws IOException, TasteException{
        Datamodel model = new FileDataModel(new File(dataFile));
        LogLikelihoodSimilarity similarity = new LogLikelihoodSimilarity(model);

        System.out.println("Simalirity between user 1 and user 3 is : ", similarity.userSimilarity(1,3));
    }
}
