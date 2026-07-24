import React from "react";
import CourseDetails from "./Components/CourseDetails";
import BookDetails from "./Components/BookDetails";
import BlogDetails from "./Components/BlogDetails";

import { courses, books, blogs } from "./data";

function App() {

  const showCourses = true;
  const showBooks = true;
  const showBlogs = true;

  // Element Variable
  let courseComponent;

  if (showCourses) {
    courseComponent = <CourseDetails courses={courses} />;
  }

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "space-around",
        margin: "50px"
      }}
    >
      {/* 1. Element Variable + if */}
      {courseComponent}

      {/* 2. Ternary Operator */}
      {showBooks ? (
        <BookDetails books={books} />
      ) : (
        <h2>No Books Available</h2>
      )}

      {/* 3. Logical AND */}
      {showBlogs && (
        <BlogDetails blogs={blogs} />
      )}
    </div>
  );
}

export default App;