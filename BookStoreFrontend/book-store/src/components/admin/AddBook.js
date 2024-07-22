import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import BookForm from "./BookForm";
import ApiService from "../../api/ApiService";
import { Spinner, Toast, ToastHeader, ToastBody } from "react-bootstrap";

function AddBook() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);

  const toast = (type, message) => {
    return (
      <Toast>
        <ToastHeader icon={type}>Reactstrap</ToastHeader>
        <ToastBody>
          <h5>{message}</h5>
        </ToastBody>
      </Toast>
    );
  };

  const handleSubmit = (file, book) => {
    setLoading(true);
    ApiService.upload(file).then((response) => {
      if (response.data.success) {
        const newBook = {
          ...book,
          attachment: response.data.data,
        };
        ApiService.addBook(newBook).then((response) => {
          if (response.data.success) {
            setLoading(false);
            toast("success", "Kitob qo'shildi");
          } else {
            setLoading(false);
            Toast.error("Book qo'shishda problemalar bor", { autoClose: 3000 });
          }
        });
      } else {
        setLoading(false);
        Toast.error("File saqlashda problema!", { autoClose: 3000 });
      }
    });
    // ApiService.addBook(file)
    // .then(response => {
    //   console.log(response.data);
    // })
    // ApiService.upload(file)
    // .then(response => {
    //   console.log(response.data);
    // })
    navigate("/");
  };

  return (
    <div>
      <h2>Add New Book</h2>
      <BookForm handleSubmit={handleSubmit} />
      {loading == false ? (
        <div></div>
      ) : (
        <div>
          <Spinner>Loading...</Spinner>
        </div>
      )}
    </div>
  );
}

export default AddBook;
