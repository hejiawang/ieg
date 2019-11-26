
ALTER TABLE ieg_school_major ADD INDEX index_school_id ( `school_id` );
ALTER TABLE ieg_school_major_enroll_record ADD INDEX index_school_major_id ( `school_major_id` );
