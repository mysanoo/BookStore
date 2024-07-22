// src/components/BookList.js
import React, { useEffect, useState } from "react";
import ApiService from "../../api/ApiService";
import "../user/bookList.css";
import {
  Card,
  CardBody,
  CardSubtitle,
  Container,
  Row,
  Col,
  CardText,
  CardImg,
  CardTitle,
  CardFooter,
  Button,
  Form,
} from "react-bootstrap";
import { Link } from "react-router-dom";
import Logout from '../auth/Logout'

const BookList = () => {
  const [books, setBooks] = useState([]);
  const [title, setTitle] = useState("");
  const role = localStorage.getItem("role");

  const storage = () => {
    console.log(localStorage);
    const user = JSON.parse(localStorage.getItem("user"));
    console.log(JSON.parse(localStorage.getItem("books")));
    console.log(user);
  }
  useEffect(() => {
    const getBooks = () => {
      try {
        ApiService.getAllBooks().then((response) => {
          setBooks(response.data.data);
          localStorage.setItem("books", JSON.stringify(response.data.data));
        });
      } catch (error) {}
    };

    getBooks();
  }, []);

  const path = "C:/Users/User/Desktop/G19/PracticeNapa/BookStore/BookStoreFrontend/book-store/assert/";

  const search = () => {
    ApiService.search(title)
    .then((response) => {
      setBooks(response.data.data);
    })
  }

  const uploadBook = () => {
    console.log("loading...");
  };

  return (
    <Container>
    <div className="col-md-1">
      <Logout/>
    </div>
    <div className="col-md-5">
    <Form className="mb-4">
      <Form.Group controlId="formTitle">
        <Form.Label>Search</Form.Label>
        <Form.Control
          type="text"
          placeholder="title"
          onChange={(e) => setTitle(e.target.value)}
        />
      </Form.Group>
      <Button onClick={search} className="mt-2">submit</Button>
    </Form>
    <Link to="/add" className="btn btn-secondary mb-3">Add</Link>
    </div>
      
      <Row>
        {books.map((book) => (
          <Col sm="4" key={book.id} className="mb-4">
            <Card>
              {book.bookImage != null ? (
                <div>
                {/* {path = `C:/Users/User/Desktop/G19/PracticeNapa/BookStore/BookStoreFrontend/book-store/assert/${encodeURIComponent(book.bookImage.name)}`}; */}
                <CardImg
                top
                width="100%"
                src="../../../assert/image_2023-10-09_18-04-39.png"
                alt="zssss"
              />
                </div>
                
              ) : (
                <CardImg
                top
                width="100%"
                src={"/public/image_2023-10-09_18-04-39.png"}
                alt={book.title}
              />
              )}
              
              <CardBody className="">
                <CardTitle tag="h1">
                  <strong className="text-muted">Title : </strong>
                  {book.title}
                </CardTitle>
                <CardSubtitle tag="h4" className="mb-2 text-muted">
                  <strong>Author : </strong>
                  {book.author}
                </CardSubtitle>
                <CardText>Description : {book.description}</CardText>
                <CardText>
                  <strong>Price:</strong> {book.price}
                </CardText>
              </CardBody>
              {role == "ROLE_ADMIN" ? (
                <CardFooter>
                <Link to={`/delete/${book.id}`} className="btn btn-secondary d-block">Delete</Link>
                <Link to={`/edit/${book.id}`} className="btn btn-secondary d-block">Update</Link>
                <Link to="/users" className="btn btn-secondary d-block">Users</Link>
                </CardFooter>
              ) : (
                <CardFooter>
                  <Button type="submit" onClick={uploadBook}>
                    upload
                  </Button>
                </CardFooter>
              )}
            </Card>
          </Col>
        ))}
      </Row>
      <Button onClick={storage}>Storage</Button>
      <img src="/public/image_2023-10-09_18-04-39.png" alt="vhjghc" />
    </Container>
  );
};

export default BookList;
