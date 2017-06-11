# Prediction of person's yearly income

Predicting if person's yearly income will be more than $50K or less.

Analyzing income of the most similar people in data set. Similarity is counted as sum of gradation ([Euclidean distance](https://en.wikipedia.org/wiki/Euclidean_distance)), power gradation ([Minkowski distance](https://en.wikipedia.org/wiki/Minkowski_distance)) and equality of every 
of 14 essential parameters such as education level, marital status, sex etc.

Data set: [https://archive.ics.uci.edu/ml/datasets/adult](https://archive.ics.uci.edu/ml/datasets/adult)

### How to use
There are two running modes:
* **-r [path]** - learning mode, reads passed CSV file and adds it to known data set.
* **-c [path]** - checking mode, checks given people and predicts their income

Here are sample [learning](https://github.com/olebokolo/50K/blob/master/src/main/resources/learn.data.csv) and [checking](https://github.com/olebokolo/50K/blob/master/src/main/resources/check.data.csv) data files.

P.S Take a break when reading [sample learning data](https://github.com/olebokolo/50K/blob/master/src/main/resources/learn.data.csv) of 32K people :)
