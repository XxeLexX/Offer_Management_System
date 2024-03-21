import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddJob from './jobs/AddJob';
import EditJob from './jobs/EditJob';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route exact path="/" element = {<Home />} />
          <Route exact path="/add_new_job" element = {<AddJob />} />
          <Route exact path="/edit_existed_job/:id" element={<EditJob />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;