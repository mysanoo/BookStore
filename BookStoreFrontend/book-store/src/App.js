import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import BookList from './components/user/BookList';
import BookDetails from './components/admin/BookDetails';
import EditBook from './components/admin/EditBook';
import DeleteBook from './components/admin/DeleteBook';
import AddBook from './components/admin/AddBook';
import SignUp from './components/auth/SignUp';
import SignIn from './components/auth/SignIn';

function App() {
  return (
    <Router>
      <div className='container mt-4'>
        <Routes>
          <Route path="/" element={<BookList />} />
          <Route path="/login" element={<SignIn />} />
          <Route path='/register' element={<SignUp />} />
          <Route path="/book/:id" element={<BookDetails />} />
          <Route path="/add" element={<AddBook />} />
          <Route path="/edit/:id" element={<EditBook />} />
          <Route path="/delete/:id" element={<DeleteBook />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
