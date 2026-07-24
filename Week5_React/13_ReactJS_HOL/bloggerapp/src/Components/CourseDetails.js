import React from "react";

function CourseDetails(props) {
  return (
    <div style={{  borderRight: "5px solid green", padding: "20px", width: "30%" }}>
      <div style={{marginLeft:"60px"}}>
        <h1>Course Details</h1>

      {props.courses.map((course) => (
        <div key={course.id}>
          <h2>{course.cname}</h2>
          <h3>{course.date}</h3>
        </div>
      ))}
      </div>
      
    </div>
  );
}

export default CourseDetails;