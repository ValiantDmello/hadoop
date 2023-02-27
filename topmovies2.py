from mrjob.job import MRJob
from mrjob.step import MRStep
from hadoop.io import HiveOutputFormat
from hadoop.io import Text

class RatingsBreakdown(MRJob):
    OUTPUT_PROTOCOL = HiveOutputFormat
    INTERNAL_PROTOCOL = Text
    OUTPUT_KEY_CLASS = Text
    OUTPUT_VALUE_CLASS = Text

    def steps(self):
        return [
            MRStep(mapper=self.mapper,
                   reducer=self.reducer1),
            MRStep(reducer=self.reducer2)
        ]

    def mapper(self, _, line):
        (userID, movieID, rating, timestamp) = line.split('\t')
        yield movieID, 1

    def reducer1(self, key, values):
        yield str(sum(values)).zfill(5), key

    def reducer2(self, count, movies):
        for movie in movies:
            yield movie, count

    def get_output_format(self):
        return HiveOutputFormat()

    def output_database(self):
        return 'default'

    def output_table(self):
        return 'movie_ratings'

if __name__ == '__main__':
    RatingsBreakdown.run()
