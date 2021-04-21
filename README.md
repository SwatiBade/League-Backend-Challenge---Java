# League Backend Challenge [Java]

## Table of Contents
- [Installation](#installation)
- [Endpoints](#endpoints)
    - [Echo](#echo)
    - [Invert](#invert)
    - [Flatten](#flatten)
    - [Sum](#sum)
    - [Multiply](#Multiply)
 - [Run using Postman](#postman)


# Installation
This Setup needs you to download the repository to your local machine
* [Java](https://www.java.com/en/download/help/mac_install.html)
* [Postman](https://www.getpostman.com/downloads/)

# Enpoints
This runs on the localhost 8080.(Given that the csv file is uploaded)
```
    1,2,3
    4,5,6
    7,8,9
```
## Echo
```
http://localhost:8080/echo
```
Output:
```
1,2,3
4,5,6
7,8,9
```
## Invert
```
http://localhost:8080/invert
```
Output:
```
1,4,7
2,5,8
3,6,9
```

## Flatten
```
http://localhost:8080/flatten
```
Output:
```
1,2,3,4,5,6,7,8,9
```

## Sum
```
http://localhost:8080/sum
```
Output:
```
45
```

## Multiply
```
http://localhost:8080/multiply
```
Output:
```
362880
```

## Postman
    1. Install the Postman[Steps are in Installation section above]
    2. Setup the java project with supporting jar (commons-csv-1.8-sources.jar, commons-csv-1.8.jar)
    3. Run the BaseWebServer. java file (if your are using eclipse or VSCode, click the run option)
    4. Start Postman
    5. Select http method as POST
    6. In the URL box enter localhost:8080/x where x is the endpoint[examples given in Endpoints above]
    7. In the body tab select form-data, select key as 'file' and value(dropdown at the end of the key column) also 'file'. Upload the matrix.csv file.
    8. Click Send

