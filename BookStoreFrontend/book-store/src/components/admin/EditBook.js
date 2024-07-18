import React from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import BookForm from './BookForm';
import ApiService from '../../api/ApiService';

function EditBook() {

    const books = JSON.parse(localStorage.getItem("books"));

    const { id } = useParams();
    const navigate = useNavigate();
    const book = books.find(book => book.id === parseInt(id));

    if (!book) return <div>Book not found</div>;

    const handleSubmit = (updatedBook) => {
    ApiService.updateBook(parseInt(id), updatedBook)
    .then(response => {
        console.log(response);
    })
    console.log('Book updated', { ...book, ...updatedBook });
    navigate('/');
  }
    return (
        <div>
            <h2>Edit Book</h2>
            <BookForm book={book} handleSubmit={handleSubmit} />
        </div>
    )
}

export default EditBook