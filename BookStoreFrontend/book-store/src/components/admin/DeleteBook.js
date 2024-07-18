import React from 'react'
import { useParams, useNavigate } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import ApiService from '../../api/ApiService';


function DeleteBook() {

    const books = JSON.parse(localStorage.getItem("books"));

    const { id } = useParams();
    const navigate = useNavigate();
    const book = books.find(book => book.id === parseInt(id));
      
    if (!book) return <div>Book not found</div>;

    const handleDelete = () => {
        ApiService.deleteBook(parseInt(id))
        .then(response => {
            console.log(response);
        })
        console.log('Book deleted', book);
        navigate('/');
    }

    return (
        <div>
            <h2>Delete Book</h2>
            <p>Are you sure you want to delete the book "{book.title}" by {book.author}?</p>
            <Button variant="danger" onClick={handleDelete}>Delete</Button>
            <Button variant="secondary" onClick={() => navigate('/')}>Cancel</Button>
        </div>
    )
}

export default DeleteBook