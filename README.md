# 🎬 Movie Recommendation System

A **Java-based Movie Recommendation System** that recommends movies to users based on their preferences. The system validates all inputs and produces personalized recommendations.

## 🧾 Description

This application reads movie and user data from input files (`movies.txt` and `users.txt`) and outputs recommendations in `recommendations.txt`.  

**Input Requirements:**

### Movies
- **Movie Title**: Each word starts with a capital letter.
- **Movie Id**: Contains all capital letters from the title followed by 3 unique numbers.
- **Movie Genre**: Genre of the movie (horror, action, drama, etc.)

### Users
- **User Name**: Alphabetic characters and spaces; must not start with a space.
- **User Id**: Alphanumeric of exact length 9, starting with numbers, optionally ending with one letter; must be unique.

### Input Files
1. `movies.txt`  
   - First line: movie title and id, separated by a comma.  
   - Second line: movie genres, separated by commas.  
   - Repeat for each movie.

2. `users.txt`  
   - First line: user name and id, separated by a comma.  
   - Second line: movie ids liked by the user, separated by commas.  
   - Repeat for each user.

## 📂 Output

The output file `recommendations.txt` contains:

1. First line: user's name and id, separated by a comma.  
2. Second line: recommended movie titles, separated by commas.  
3. Repeat for each user.

### 🧠 Recommendation Logic

- If a user likes a movie from genre X, the system recommends all other movies in that genre.  
- If any input is invalid, the output contains the first error encountered:
- `ERROR: Movie Title {movie_title} is wrong`
- `ERROR: Movie Id letters {movie_id} are wrong`
- `ERROR: Movie Id numbers {movie_id} aren’t unique`
- `ERROR: User Name {user_name} is wrong`
- `ERROR: User Id {user_id} is wrong`

  ## 🛠️ Requirements & Testing

- Application must validate inputs before processing.
- Test each unit separately.
- Required testing:
- White box testing
- Data flow testing
- Black box testing
- Integration testing
- Group project: 6 students per group, one submission per group.

## 📂 Project Structure
```
movie-recommendation-system/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── mainPackage/
│   │           ├── AppExceptions.java
│   │           ├── ErrorCode.java
│   │           ├── FileParser.java
│   │           ├── Main.java
│   │           ├── Movie.java
│   │           ├── MovieValidator.java
│   │           ├── OutputGenerator.java
│   │           ├── RecommendationEngine.java
│   │           ├── User.java
│   │           ├── UserValidator.java
│   │           └── ValidationService.java
│   └── test/
│       └── java/
│           ├── BlackBoxTesting/
│           ├── IntegrationTesting/
│           ├── UnitTesting/
│           └── WhiteBoxTesting/
├── resources/
├── target/
├── movies.txt
├── users.txt
├── recommendations.txt
├── pom.xml
├── README.md
└── .gitignore

```


