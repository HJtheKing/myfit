CREATE DATABASE IF NOT EXISTS `SSAFIT`;
USE `SSAFIT`;

# 사용자
CREATE TABLE IF NOT EXISTS `member` (
    `member_id` INT AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(50) UNIQUE NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `alternate_email` VARCHAR(50),
    `name` VARCHAR(50) NOT NULL,
    `birth_date` TIMESTAMP NOT NULL,
    `phone` VARCHAR(15) NOT NULL,
    `signup_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `role` INT NOT NULL DEFAULT 1,
    `temp1` TEXT,
    `temp2` TEXT
);

# 인바디
CREATE TABLE IF NOT EXISTS `inbody_data` (
    `inbody_id` INT AUTO_INCREMENT PRIMARY KEY,
    `member_id` INT NOT NULL,
    `height` FLOAT NOT NULL,                     -- 신장(키)
    `age` INT NOT NULL,                          -- 연령
    `gender` VARCHAR(6) NOT NULL,                -- 성별
    `body_fat_mass` FLOAT NOT NULL,              -- 체지방량
    `weight` FLOAT NOT NULL,                     -- 체중
    `skeletal_muscle_mass` FLOAT NOT NULL,       -- 골격근량
    `ideal_weight` FLOAT NOT NULL,               -- 적정체중
    `weight_control` FLOAT NOT NULL,             -- 체중조절
    `fat_control` FLOAT NOT NULL,                -- 지방 조절
    `muscle_control` FLOAT NOT NULL,             -- 근육 조절
    `basal_metabolic_rate` FLOAT NOT NULL,       -- 기초대사량
    `recommended_intake_calories` FLOAT NOT NULL,-- 권장섭취열량
    `left_arm_muscle` VARCHAR(4) NOT NULL,       -- 왼팔 근육
    `right_arm_muscle` VARCHAR(4) NOT NULL,      -- 오른팔 근육
    `trunk_muscle` VARCHAR(4) NOT NULL,          -- 몸통 근육
    `left_leg_muscle` VARCHAR(4) NOT NULL,       -- 왼다리 근육
    `right_leg_muscle` VARCHAR(4) NOT NULL,      -- 오른다리 근육
    `left_arm_fat` VARCHAR(4) NOT NULL,          -- 왼팔 지방
    `right_arm_fat` VARCHAR(4) NOT NULL,         -- 오른팔 지방
    `trunk_fat` VARCHAR(4) NOT NULL,             -- 몸통 지방
    `left_leg_fat` VARCHAR(4) NOT NULL,          -- 왼다리 지방
    `right_leg_fat` VARCHAR(4) NOT NULL,         -- 오른다리 지방
    `measurement_date` TIMESTAMP NOT NULL,
    FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE
);

# 선호 운동 부위
CREATE TABLE IF NOT EXISTS `preferred_exercise_area` (
    `area_id` INT AUTO_INCREMENT PRIMARY KEY,
    `member_id` INT NOT NULL,
    `exercise_area` VARCHAR(50) NOT NULL,
    FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE
);

# 비디오
CREATE TABLE IF NOT EXISTS `video` (
    `video_id` INT AUTO_INCREMENT PRIMARY KEY,
    `channel_name` VARCHAR(50) NOT NULL,
    `youtube_id` VARCHAR(15) NOT NULL UNIQUE,
    `title` VARCHAR(100) NOT NULL,
    `exercise_area` VARCHAR(50) NOT NULL,
    `view_cnt` INT NOT NULL DEFAULT 0,
    `like_cnt` INT NOT NULL DEFAULT 0,
    `temp1` TEXT,
    `temp2` TEXT
);

# 포스트 (기존 리뷰 테이블)
CREATE TABLE IF NOT EXISTS `post` (
    `post_id` INT AUTO_INCREMENT PRIMARY KEY,
    `video_id` INT NOT NULL,
    `member_id` INT NOT NULL,
    `title` VARCHAR(30) NOT NULL,
    `content` TEXT NOT NULL,
    `img_src1` TEXT,
    `img_src2` TEXT,
    `img_src3` TEXT,
    `reg_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `temp1` TEXT,
    `temp2` TEXT,
    FOREIGN KEY (`video_id`) REFERENCES `video` (`video_id`),
    FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE
);

# 팔로우
CREATE TABLE IF NOT EXISTS `follow` (
    `follow_id` INT AUTO_INCREMENT PRIMARY KEY,
    `member_id` INT NOT NULL,
    `following_id` INT NOT NULL,
    FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE,
    FOREIGN KEY (`following_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE
);

# 찜
CREATE TABLE IF NOT EXISTS `like_data` (
    `like_id` INT AUTO_INCREMENT PRIMARY KEY,
    `member_id` INT NOT NULL,
    `video_id` INT NOT NULL,
    FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE,
    FOREIGN KEY (`video_id`) REFERENCES `video` (`video_id`)
);

# 식단
CREATE TABLE `diet` (
    `diet_id` INT AUTO_INCREMENT PRIMARY KEY,
    `member_id` INT NOT NULL,
    `purpose` ENUM('Bulkup', 'Diet', 'Healthy') NOT NULL,
    `allergie` VARCHAR(30),
    `past_meal` VARCHAR(30),
    `food_list` TEXT NOT NULL,
    `calories` INT NOT NULL,
    `meal_time` ENUM('Breakfast', 'Lunch', 'Dinner', 'Snack') NOT NULL,
    `additional_info` TEXT,
    FOREIGN KEY (`member_id`) REFERENCES `member`(`member_id`) ON DELETE CASCADE
);

# 조회 메서드
select * from `member`;
select * from video;
select * from post;
select * from like_data;
select * from inbody_data;
select * from preferred_exercise_area;
select * from diet;

# 초기 데이터
-- 1. member
INSERT INTO `` (`member_id`,`email`,`password`,`alternate_email`,`name`,`birth_date`,`phone`,`signup_date`,`role`,`temp1`,`temp2`) VALUES (1,'ssafy@ssafy.com','123456','alter@ssafy.com','닉네임','2000-01-01 00:00:00','010-1234-5678','2024-05-24 01:27:26',1,NULL,NULL);
INSERT INTO `` (`member_id`,`email`,`password`,`alternate_email`,`name`,`birth_date`,`phone`,`signup_date`,`role`,`temp1`,`temp2`) VALUES (2,'ssafy1@ssafy.com','123456','alternate@email.com','닉네임입니다.','2000-01-01 00:00:00','010-1234-5678','2024-05-24 01:42:46',1,NULL,NULL);

-- 2. video
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (1,'헬마드 (헬스 건강 정보)','zXo9WE_tI18','🔥하체 운동 루틴🔥헬스장에서 해야 할 필수 종목 BEST 4','하체',2,0,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (2,'힙으뜸','js8z5wIZ0wg','[ENG] (층간소음X, 설명O) 진짜 힘듦주의.. 🔥초강력🔥 힙으뜸 하체운동 2주 챌린지','하체',3,0,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (3,'강경원','35mO-32HukE','힘들고 재미없다고? 하체 운동의 중요성! | 운동이야기 22화','하체',37,1,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (4,'힙으뜸','zruKSHfD7b4','[ENG] 딱 20분🔥 하루 운동 끝장내는 전신 &amp; 아랫배 &amp; 타바타 올인원 루틴','전신',0,0,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (5,'소미핏 SOMIFIT','R6GHtRJLKQg','[7분] 고강도 전신, 기적의 체지방감량 운동! 통통살 컴온! (층소X)','전신',51,2,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (6,'비타민신지니 VitaminJINY','N7W4mvOyxIk','와...르세라핌이 ‘이 동작’으로 살을 뺐구나....[극강의 9분 전신운동]','전신',0,0,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (7,'HOMEWORKOUT','xA3joqtfDt8','가장 짧고 강력한 전신운동! 힘든 만큼 효과 보장','전신',0,0,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (8,'홍범석 hongbeomseok','FS_hmVHlFVs','달리기에 최적화된 5분하체운동! 하체 중량운동을 하지 않는 이유?','하체',0,0,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (9,'빵느','jjTjeNghWoM','🔥단시간 칼로리 소모 2배🔥 20분 유산소운동 (No 스쿼트, No 런지, No 층간소음)','유산소',9,1,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (10,'빅씨스 Bigsis','4kZHHPH6heY','NO 층간소음 - 고민없이 하나로 끝내는 전신운동 근력 유산소 - 운동 못한 날 죄책감 씻어줄 30분 홈트','유산소',1,0,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (11,'한혜진 Han Hye Jin','n8zGSCTd1bA','톱모델 한혜진 살뺄 때 하는 15분 근력유산소 (한번만 해보세요)','유산소',6,0,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (12,'뻔더','KX-rUFpD95M','초보자 복근운동 이것 하나만 해보세요','몸통',0,0,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (13,'보통사람을 위한 운동채널','9yqYgn2tCL0','헬스할 때 &#39;몸통 두께&#39;부터 키워야 하는 이유?(등과 흉곽을 두텁게 만드는 운동루틴)','몸통',0,0,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (14,'이지은 다이어트 Jiny diet','h7cZIEcqB10','♦️두꺼운 배둘레햄♦️ 에서 탈출! 잘록허리 만들기!','몸통',0,0,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (15,'건강과 운동은 과학이다','AC5km--KdIY','두꺼운 몸통은 어떻게 만들어지는가?','몸통',0,0,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (16,'근육TV','ZuVKprz4nlo','팔뚝 가장 빠르게 키우는 팔 운동 루틴?! (이두 운동 루틴,팔 두꺼워 지는 운동)','팔',0,0,NULL,NULL);
INSERT INTO `` (`video_id`,`channel_name`,`youtube_id`,`title`,`exercise_area`,`view_cnt`,`like_cnt`,`temp1`,`temp2`) VALUES (17,'헬마드 (헬스 건강 정보)','f-8XH5wiz_M','💪팔 운동 루틴💪팔 근육 전체를 한번에 끝내는 &#39;4종목 16세트&#39;','팔',0,0,NULL,NULL);

-- 3. post
INSERT INTO `` (`post_id`,`video_id`,`member_id`,`title`,`content`,`img_src1`,`img_src2`,`img_src3`,`reg_date`,`temp1`,`temp2`) VALUES (1,5,1,'제목입니다.','내용입니다.','eb63e3c5-d0ae-46ce-a701-5aa171211e40.jpg','45abd9cf-815a-4e6c-97dd-9fdf3a1e69eb.jpg','cc76d952-8642-4aed-86cf-87ec5e01dd07.jpg','2024-05-24 04:52:58',NULL,NULL);

-- 4. like
INSERT INTO `` (`like_id`,`member_id`,`video_id`) VALUES (13,2,3);
INSERT INTO `` (`like_id`,`member_id`,`video_id`) VALUES (14,2,5);
INSERT INTO `` (`like_id`,`member_id`,`video_id`) VALUES (15,2,9);
INSERT INTO `` (`like_id`,`member_id`,`video_id`) VALUES (20,1,5);

-- 5. inbody
INSERT INTO `` (`inbody_id`,`member_id`,`height`,`age`,`gender`,`body_fat_mass`,`weight`,`skeletal_muscle_mass`,`ideal_weight`,`weight_control`,`fat_control`,`muscle_control`,`basal_metabolic_rate`,`recommended_intake_calories`,`left_arm_muscle`,`right_arm_muscle`,`trunk_muscle`,`left_leg_muscle`,`right_leg_muscle`,`left_arm_fat`,`right_arm_fat`,`trunk_fat`,`left_leg_fat`,`right_leg_fat`,`measurement_date`) VALUES (6,2,156.9,51,'여성',23.8,60.3,19.5,52.9,-7.4,-10.9,3.5,1168,1397,'표준','표준','표준','표준이하','표준이하','표준이상','표준이상','표준이상','표준','표준','2021-05-30 00:00:00');
