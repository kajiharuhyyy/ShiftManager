INSERT INTO staff (name, email, active) VALUES
('山田 太郎', 'taro@example.com', TRUE),
('佐藤 花子', 'hanako@example.com', TRUE),
('中村 一郎', 'ichiro@example.com', TRUE);

INSERT INTO shifts (staff_id, work_date, start_time, end_time, note) VALUES
(1, '2024-07-01', '09:00:00', '17:00:00', '通常勤務'),
(2, '2024-07-01', '10:00:00', '18:00:00', '遅番勤務'),
(3, '2024-07-01', '08:30:00', '16:30:00', '早番勤務');