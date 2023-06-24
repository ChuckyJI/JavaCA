import React from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

const Home = () => {
    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <Button color="link"><Link to="/student">StudentController</Link></Button>
                <Button color="link"><Link to="/lecturer">LecturerController</Link></Button>
                <Button color="link"><Link to="/module">ModuleController</Link></Button>
            </Container>
        </div>
    );
}

export default Home;