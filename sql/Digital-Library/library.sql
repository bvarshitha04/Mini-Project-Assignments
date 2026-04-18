-- CREATE TABLES

CREATE TABLE Books (
    book_id INT PRIMARY KEY,
    title VARCHAR(100),
    category VARCHAR(50)
);

CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE IssuedBooks (
    issue_id INT PRIMARY KEY,
    book_id INT,
    student_id INT,
    issue_date DATE,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES Books(book_id),
    FOREIGN KEY (student_id) REFERENCES Students(student_id)
);

-- INSERT DATA

INSERT INTO Books VALUES
(1, 'Java Basics', 'Education'),
(2, 'Python Guide', 'Education'),
(3, 'History of India', 'History'),
(4, 'AI Fundamentals', 'Technology');

INSERT INTO Students VALUES
(1, 'Rahul'),
(2, 'Anjali'),
(3, 'Kiran');

INSERT INTO IssuedBooks VALUES
(1, 1, 1, '2026-03-01', NULL),
(2, 2, 2, '2026-03-20', '2026-03-25'),
(3, 3, 3, '2026-03-10', NULL),
(4, 4, 1, '2026-03-05', '2026-03-07');

-- QUERY 1: OVERDUE BOOKS

SELECT s.name, b.title, i.issue_date
FROM IssuedBooks i
JOIN Students s ON i.student_id = s.student_id
JOIN Books b ON i.book_id = b.book_id
WHERE i.return_date IS NULL
AND DATEDIFF(CURDATE(), i.issue_date) > 14;

-- QUERY 2: POPULAR CATEGORY

SELECT b.category, COUNT(*) AS total_borrows
FROM IssuedBooks i
JOIN Books b ON i.book_id = b.book_id
GROUP BY b.category
ORDER BY total_borrows DESC;