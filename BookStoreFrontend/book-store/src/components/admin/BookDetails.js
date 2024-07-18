// src/components/BookDetails.js
import React from 'react';
import { useParams, Link } from 'react-router-dom';

const books = [
  { id: 1, title: 'Book 1', author: 'Author 1', description: 'Description 1' },
  { id: 2, title: 'Book 2', author: 'Author 2', description: 'Description 2' },
];

const BookDetails = () => {
  const { id } = useParams();
  const book = books.find(book => book.id === parseInt(id));

  if (!book) return <div>Book not found</div>;

  return (
    <div>
      <h2>{book.title}</h2>
      <p><strong>Author:</strong> {book.author}</p>
      <p><strong>Description:</strong> {book.description}</p>
      <Link to="/" className="btn btn-primary">Back to List</Link>
    </div>
  );
}

export default BookDetails;
