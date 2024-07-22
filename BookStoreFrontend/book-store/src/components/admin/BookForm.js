// src/components/BookForm.js
import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';

const BookForm = ({ book, handleSubmit, upload }) => {
  const [title, setTitle] = useState(book ? book.title : '');
  const [author, setAuthor] = useState(book ? book.author : '');
  const [price, setPrice] = useState(book ? book.price : '');
  const [description, setDescription] = useState(book ? book.description : '');
  const [file, setFile] = useState(null);

  const onSubmit = (e) => {
    e.preventDefault();
    const book = {
      title : title,
      author : author,
      price : price,
      description : description
    }

    const formData = new FormData;
    formData.append("file", file);
    handleSubmit(formData, book);
  }


  return (
    <Form onSubmit={onSubmit}>
    <Form.Group controlId="formTitle">
        <Form.Label>Upload Image</Form.Label>
        <Form.Control
          type="file"
          placeholder="Upload book"
          onChange={(e) => setFile(e.target.files[0])}
          // value={file}
        />
      </Form.Group>

      <Form.Group controlId="formTitle">
        <Form.Label>Title</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group controlId="formAuthor">
        <Form.Label>Author</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter author"
          value={author}
          onChange={(e) => setAuthor(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group controlId="formDescription">
        <Form.Label>Description</Form.Label>
        <Form.Control
          as="textarea"
          rows={3}
          placeholder="Enter description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </Form.Group>

      <Form.Group controlId="formPrice">
        <Form.Label>Price</Form.Label>
        <Form.Control
          type="number"
          placeholder="Enter price"
          onChange={(e) => setPrice(e.target.value)}
          value={price}
          required
        />
      </Form.Group>

      <Button variant="primary" type="submit"  className='mt-3'>
        Submit
      </Button>
    </Form>
  );
}

export default BookForm;
