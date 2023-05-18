-- campus table
delete from section;
delete from building;
delete from program;
delete from student;
delete from campus;
insert into `campus` (name) values
('seattle');
select * from campus;

-- building table
delete from section;
delete from building;
insert into `building` (abbreviation, `description`, `location`, `department`, `campusName`) values
('MGH', 'for info students', 'center of the campus', '[{\"dep\":\"info\"},{\"dep\":\"cse\"}]', 'seattle'),
('KNE', 'for all students', 'red squd', '[{\"dep\":\"all\"}]', 'seattle');
select campus.*, building.*
from building
join campus on building.campusName = campus.name;

-- student table
delete from student;
insert into `student` (netId, `phone`, `email`, `isShared`, `campusName`) values
(123456, '20611111111', 'yjr@uw.edu', '0', 'seattle');
select campus.*, student.*
from student
join campus on student.campusName = campus.name;

-- quarter table
delete from quarter;
insert into `quarter` (title, `registrationPreview`, `registeredCourses`) values
('spring', 'None', 'three courses');
select * from quarter;

-- schedule table
delete from course;
delete from schedule;
insert into `schedule` (id, `registeredSchedule`, `tips`) values
(1, 'schedule 1', '[{\"conflicted\":\"yes\", \"less than 18\":\"no\"}]'),
(2, 'schedule 2', '[{\"conflicted\":\"no\", \"less than 18\":\"no\"}]');
select * from schedule;

-- program table
delete from program;
insert into `program` (name, `isInBookmark`, `description`, `departmentInfo`, `isMajor`, `admissionType`, `degreeRequirement`, `campusName`) values
('math', '1', 'learning calculus', 'calculating', '0', '[{\"type\":\"1\",\"hard\":\"no\"}]', '[{\"prereq\":\"math 124\"},{\"prereq\":\"math 125\"},{\"prereq\":\"math 126\"}]', 'seattle'),
('informatics', '1', 'information study', 'writing and programming', '0', '[{\"type\":\"2\",\"hard\":\"very\"}]', '[{\"prereq\":\"info 200\"},{\"prereq\":\"stat 311\"}]', 'seattle');
select program.*, campus.*
from program
join campus on program.campusName = campus.name;

-- course table
delete from section;
delete from course;
insert into `course` (id, `title`, `description`, `prerequisite`, `isInBookmark`, `genEduReq`, `scheduleId`) values
(100, 'INFO 330', 'database knowledge', 'None', '1', 'NW', '1'),
(101, 'INFO 340', 'client-side development', 'INFO 330', '0', 'VLPA', '2');
select schedule.*, course.*
from course
join schedule on course.scheduleId = schedule.id;

-- section table
delete from section;
insert into section (sln, `type`, `instructor`, `additionalDetail`, `time`, `status`, `sectioncol5`, `courseId`, `buildingAbbr`) values
(10343, 'lecture', 'bob', '[{\"Major req\":\"to all\"}]', '10:30', 'open', 'AA', '100', 'MGH'),
(10133, 'lecture', 'wes', '[{\"Major req\":\"only to info\"}]', '10:10', 'open', 'C', '101', 'KNE');
select course.id, section.*
from section
join course on section.courseId = course.id
join building on section.buildingAbbr = building.abbreviation;


-- course-program table
delete from `course-program`;
insert into `course-program` (courseId, programName) values
(100, 'informatics'),
(101, 'math');
select course.*, program.*, `course-program`.*
from `course-program`
join course on `course-program`.courseId = course.id
join program on `course-program`.programName = program.name;

-- quarter-section table
delete from `quarter-section`;
insert into `quarter-section` (quarterTitle, sectionSln) values
('spring', '10343');
select quarter.*, section.*, `quarter-section`.*
from `quarter-section`
join quarter on `quarter-section`.quarterTitle = quarter.title
join section on `quarter-section`.sectionSln = section.sln;

-- student-course table
delete from `student-course`;
insert into `student-course` (studentNetId, courseId) values
(123456, 100),
(123456, 101);
select student.*, course.*, `student-course`.*
from `student-course`
join student on `student-course`.studentNetId = student.netId
join course on `student-course`.courseId = course.id;