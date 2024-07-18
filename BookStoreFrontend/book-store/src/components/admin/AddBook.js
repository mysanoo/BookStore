import React from 'react'
import { useNavigate } from "react-router-dom"
import BookForm from './BookForm';
import ApiService from '../../api/ApiService';

function AddBook() {
    const navigate = useNavigate();

  const handleSubmit = (book, file) => {
    ApiService.addBook(book)
    .then(response => {
      console.log(response.data);
    })
    ApiService.upload(file)
    .then(response => {
      console.log(response.data);
    })
    navigate('/');
  }


  return (
    <div>
        <h2>Add New Book</h2>
        <BookForm handleSubmit={handleSubmit} />
    </div>
  )
}

export default AddBook