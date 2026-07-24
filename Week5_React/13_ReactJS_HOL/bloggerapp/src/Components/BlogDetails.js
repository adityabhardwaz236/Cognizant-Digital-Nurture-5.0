import React from "react";

function BlogDetails(props) {
  return (
    <div style={{ padding: "20px", width: "30%", marginLeft:"20px" }}>
      <h1>Blog Details</h1>

      {props.blogs.map((blog) => (
        <div key={blog.id}>
          <h2>{blog.title}</h2>
          <h4>{blog.author}</h4>
          <p>{blog.content}</p>
        </div>
      ))}
    </div>
  );
}

export default BlogDetails;