-- Database Setup
CREATE DATABASE IF NOT EXISTS MoviePlatform;
USE MoviePlatform;

-- Drop tables if they exist to allow clean re-runs
DROP TABLE IF EXISTS Watch_History;
DROP TABLE IF EXISTS Ratings;
DROP TABLE IF EXISTS Movies;
DROP TABLE IF EXISTS Users;

-- 1. Create Tables
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT
);

CREATE TABLE Movies (
    movie_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    genre VARCHAR(50)
);

CREATE TABLE Ratings (
    rating_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    movie_id INT,
    rating DECIMAL(3, 1),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id)
);

CREATE TABLE Watch_History (
    history_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    movie_id INT,
    watch_date DATE,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id)
);

-- 2. Insert Dummy Data
INSERT INTO Users (name, age) VALUES 
('Alice', 25), ('Bob', 30), ('Charlie', 22), ('Diana', 28);

INSERT INTO Movies (title, genre) VALUES 
('Inception', 'Sci-Fi'),
('The Matrix', 'Sci-Fi'),
('Titanic', 'Romance'),
('The Dark Knight', 'Action'),
('Avengers', 'Action');

INSERT INTO Ratings (user_id, movie_id, rating) VALUES 
(1, 1, 5.0), (1, 2, 4.5), (1, 4, 4.0),
(2, 1, 4.0), (2, 4, 5.0), (2, 5, 4.5),
(3, 3, 5.0), (3, 1, 3.0),
(4, 4, 4.5), (4, 5, 5.0);

INSERT INTO Watch_History (user_id, movie_id, watch_date) VALUES 
(1, 1, DATE_SUB(CURRENT_DATE, INTERVAL 2 DAY)),
(1, 2, DATE_SUB(CURRENT_DATE, INTERVAL 5 DAY)),
(1, 4, DATE_SUB(CURRENT_DATE, INTERVAL 10 DAY)),
(2, 1, DATE_SUB(CURRENT_DATE, INTERVAL 1 DAY)),
(2, 4, CURRENT_DATE),
(2, 5, DATE_SUB(CURRENT_DATE, INTERVAL 3 DAY)),
(3, 3, DATE_SUB(CURRENT_DATE, INTERVAL 1 DAY)),
(3, 1, DATE_SUB(CURRENT_DATE, INTERVAL 15 DAY)),
(4, 4, CURRENT_DATE),
(4, 5, CURRENT_DATE);

-- ---------------------------------------------------------
-- KEY TASKS (REPORTS)
-- ---------------------------------------------------------

-- Task 1: Find top-rated movies (Average rating >= 4.0)
SELECT 
    m.title, 
    ROUND(AVG(r.rating), 1) as average_rating
FROM Movies m
JOIN Ratings r ON m.movie_id = r.movie_id
GROUP BY m.title
HAVING average_rating >= 4.0
ORDER BY average_rating DESC;


-- Task 2: Identify most popular genres (Based on watch history count)
SELECT 
    m.genre, 
    COUNT(w.history_id) as total_watches
FROM Movies m
JOIN Watch_History w ON m.movie_id = w.movie_id
GROUP BY m.genre
ORDER BY total_watches DESC;


-- Task 3: Recommend movies based on similar users 
-- Example: Recommend movies to User 1 (Alice) based on what other users who watched the same movies liked
SELECT DISTINCT m.title AS Recommended_Movies_For_Alice
FROM Watch_History w1
JOIN Watch_History w2 ON w1.movie_id = w2.movie_id -- Find users who watched the same movies as Alice
JOIN Watch_History w3 ON w2.user_id = w3.user_id -- Find what else those similar users watched
JOIN Movies m ON w3.movie_id = m.movie_id
WHERE w1.user_id = 1 -- Alice
  AND w2.user_id != 1 -- Other users
  AND w3.movie_id NOT IN (SELECT movie_id FROM Watch_History WHERE user_id = 1); -- Exclude movies Alice already watched


-- Task 4: Analyze user behavior patterns 
-- (Total movies watched and average rating given by each user)
SELECT 
    u.name, 
    COUNT(DISTINCT w.movie_id) as total_movies_watched, 
    ROUND(AVG(r.rating), 1) as avg_rating_given
FROM Users u
LEFT JOIN Watch_History w ON u.user_id = w.user_id
LEFT JOIN Ratings r ON u.user_id = r.user_id
GROUP BY u.name;


-- Task 5: Detect trending movies 
-- (Movies watched most frequently in the last 7 days)
SELECT 
    m.title, 
    COUNT(w.history_id) as recent_watches
FROM Movies m
JOIN Watch_History w ON m.movie_id = w.movie_id
WHERE w.watch_date >= DATE_SUB(CURRENT_DATE, INTERVAL 7 DAY)
GROUP BY m.title
ORDER BY recent_watches DESC
LIMIT 5;
