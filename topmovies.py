from mrjob.job import MRJob
from mrjob.step import MRStep

class RatingsBreakdown(MRJob):
    def steps(self):
        return [
            MRStep(mapper=self.mapper,
                   reducer=self.reducer1),
            MRStep(reducer=self.reducer2)
        ]

    def mapper(self, _, line):  #mapper function
        (userID, movieID, rating, timestamp) = line.split('\t')
        yield movieID, 1

    def reducer1(self, key, values):   #reducer to get ratings count
        yield str(sum(values)).zfill(5), key

    def reducer2(self, count, movies): #reducer to sort as per most ratings
        for movie in movies:
            yield movie, count


if __name__ == '__main__':
    RatingsBreakdown.run()