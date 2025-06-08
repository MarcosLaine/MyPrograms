import pandas as pd
import requests
from io import StringIO

# URLs for the Titanic dataset
train_url = "https://raw.githubusercontent.com/datasciencedojo/datasets/master/titanic.csv"
test_url = "https://raw.githubusercontent.com/datasciencedojo/datasets/master/titanic.csv"

# Download and save train data
response = requests.get(train_url)
train_data = pd.read_csv(StringIO(response.text))
train_data.to_csv("train.csv", index=False)

# Download and save test data
response = requests.get(test_url)
test_data = pd.read_csv(StringIO(response.text))
test_data.to_csv("test.csv", index=False)

print("Dataset files downloaded successfully!") 