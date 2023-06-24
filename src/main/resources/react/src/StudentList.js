import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

const StudentList = () => {

    const [students, setStudent] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);

        fetch('/student')
            .then(response => response.json())
            .then(data => {
                setStudent(data);
                setLoading(false);
            })
    }, []);

    const remove = async (id) => {
        await fetch(`/student/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedGroups = [...students].filter(i => i.id !== id);
            setStudent(updatedGroups);
        });
    }

    if (loading) {
        return <p>Loading...</p>;
    }

    const studentList = students.map(student => {
        const name = `${student.firstName || ''} ${student.secondName || ''} `;
        return <tr key={student.id}>
            <td style={{whiteSpace: 'nowrap'}}>{student.id}</td>
            <td>{name}</td>
            <td>{student.events.map(event => (
                <div key={event.id}>
                    {new Intl.DateTimeFormat('en-US', {
                        year: 'numeric',
                        month: 'long',
                        day: '2-digit'
                    }).format(new Date(event.date))}: {event.title}
                </div>
            ))}</td>
            <td>
                <ButtonGroup>
                    <Button size="sm" color="primary" tag={Link} to={"/student/" + student.id}>Edit</Button>
                    <Button size="sm" color="danger" onClick={() => remove(student.id)}>Delete</Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <div className="float-end">
                    <Button color="success" tag={Link} to="/student">Add Group</Button>
                </div>
                <h3>My JUG Tour</h3>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="20%">Name</th>
                        <th width="20%">Location</th>
                        <th>Events</th>
                        <th width="10%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {students}
                    </tbody>
                </Table>
            </Container>
        </div>
    );
};

export default StudentList;