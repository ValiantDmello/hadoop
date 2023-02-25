import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class Colaborative {

  public static void main(String[] args) throws IOException, TasteException {

    // Read the data file
    File dataFile = new File("data/u.data");
    DataModel dataModel = new FileDataModel(dataFile);

    // Compute item similarity
    UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
    GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel, similarity);

    // Generate recommendations
    List<RecommendedItem> recommendations = recommender.recommend(1, 10);

    // Print recommendations
    for (RecommendedItem recommendation : recommendations) {
      System.out.println(recommendation);
    }
  }
}
