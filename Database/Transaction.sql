-- ***********************
-- Create (INSERT) Queries
-- ***********************

-- addOrUpdateStudent
select * from student;
INSERT INTO `student` (netId,`phone`, `email`, `isShared`, `campusName`) VALUES 
(1,'20633333333', '1@uw.edu', '0', 'seattle')
on duplicate key update phone=values(phone), email=values(email), isShared=values(isShared), campusName=values(campusName)
;
select * from student;

-- addOrUpdateQuarter
select * from Quarter;
INSERT INTO `Quarter` (title,`registrationPreview`, `registeredCourses`) VALUES 
('spring','spring 2022', 'math124')
on duplicate key update title=values(title), registrationPreview=values(registrationPreview), registeredCourses=values(registeredCourses)
;
select * from Quarter;

-- addOrUpdateSection
select * from Section;
INSERT INTO `Section` (sln, `type`, `instructor`, `additionalDetail`, `time`, `status`, `sectioncol5`, `courseId`, `buildingAbbr`) VALUES 
(10343, 'lecture', 'bob', '[{\"Major req\":\"to all\"}]', '10:30', 'open', 'AA', '100', 'MGH')
on duplicate key update sln=values(sln), type=values(type), instructor=values(instructor), additionalDetail=values(additionalDetail), time=values(time), status=values(status),
sectioncol5=values(sectioncol5), courseId=values(courseId), buildingAbbr=values(buildingAbbr)
;
select * from Section;

-- *************************
-- Retireve (SELECT) Queries
-- *************************

-- getStudentCourse
select student.* 
from student
join `student-course`  on `student-course`.studentNetId = student.netId
join course on `student-course`.courseId = course.id
where netid = 123456
;

-- getQuarterSection
select quarter.*
from quarter
join `quarter-section`  on `quarter-section`.quarterTitle = quarter.title
join section on `quarter-section`.sectionSln = section.sln
where quarter.title = 'spring'
;


-- getCard
-- What it does: Select all the data in a card and its related tables
-- Select the activities for the card
-- Select the users for the card
-- Select the labels for the card
-- Select the checklists for the card

-- *****************
-- Update Queries
-- *****************

-- updateStudent
select * from student;
update student set `isShared`= 1 where netid=1;
select * from student order by netid, `isShared`;

-- updateProgram
select * from program;
update program set `isMajor`= 1 where name=informatics;
select * from program order by name, `isMajor`;

-- updateSchedule
select * from schedule;
update schedule set `registeredSchedule`= '10' where id=1;
select * from schedule order by id, `registeredSchedule`;

-- updateSchedule
select * from schedule;
update schedule set `registeredSchedule`= ''11 where id=2;
select * from schedule order by id, `registeredSchedule`;


-- *****************
-- Delete Queries
-- *****************

-- deleteStudent
select * from student;
delete from student where netid = 1;
select * from student;

-- deleteQuarter
select * from quarter;
delete from quarter where title = 'spring';
select * from quarter;

-- deleteSection1
select * from section;
delete from section where sln = 10133;
select * from section;

-- deleteSection2
select * from section;
delete from section where sln = 10343;
select * from section;

-- deleteSchedule
select * from schedule;
delete from schedule where id = 1;
select * from schedule;

-- deleteCard
select * from schedule;
delete from schedule where id = 2;
select * from schedule;