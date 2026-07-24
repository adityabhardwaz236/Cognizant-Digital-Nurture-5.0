import React from "react";

function BookDetails(props) {
  return (
    <div style={{ borderRight: "5px solid green", padding: "20px", width: "30%", marginLeft:"20px" }}>
      <h1>Book Details</h1>

      {props.books.map((book) => (
        <div key={book.id} style={{marginLeft:"45px"}}>
          <h3>{book.bname}</h3>
          <h4>{book.price}</h4>
        </div>
      ))}
    </div>
  );
}

export default BookDetails;