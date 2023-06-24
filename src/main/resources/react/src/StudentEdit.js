import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

const StudentEdit = () => {
    const initialFormState = {
        surname:'',
        firstName: '',
        secondName: '',
        age: '',
        nationality: '',
        email: '',
        gpa: '',
        studentID: '',
        originCountry: ''
    };
    const [student, setStudent] = useState(initialFormState);
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id !== 'new') {
            fetch(`/student/${id}`)
                .then(response => response.json())
                .then(data => setStudent(data));
        }
    }, [id, setStudent()]);

    const handleChange = (event) => {
        const { name, value } = event.target

        setStudent({ ...student, [name]: value })
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        await fetch(`/student/${student.id ? `/${student.id}` : ''}`, {
            method: (student.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(student)
        });
        setStudent(initialFormState);
        navigate('/student');
    }

    const title = <h2>{student.id ? 'Edit Group' : 'Add Group'}</h2>;

    return (<div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={student.firstName || ''}
                               onChange={handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="address">Address</Label>
                        <Input type="text" name="address" id="address" value={student.nationality || ''}
                               onChange={handleChange} autoComplete="address-level1"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="city">City</Label>
                        <Input type="text" name="city" id="city" value={student.age || ''}
                               onChange={handleChange} autoComplete="address-level1"/>
                    </FormGroup>
                    <div className="row">
                        <FormGroup className="col-md-4 mb-3">
                            <Label for="stateOrProvince">State/Province</Label>
                            <Input type="text" name="stateOrProvince" id="stateOrProvince" value={student.originCountry || ''}
                                   onChange={handleChange} autoComplete="address-level1"/>
                        </FormGroup>
                        <FormGroup className="col-md-5 mb-3">
                            <Label for="country">Country</Label>
                            <Input type="text" name="country" id="country" value={student.nationality || ''}
                                   onChange={handleChange} autoComplete="address-level1"/>
                        </FormGroup>
                        <FormGroup className="col-md-3 mb-3">
                            <Label for="country">Postal Code</Label>
                            <Input type="text" name="postalCode" id="postalCode" value={student.age || ''}
                                   onChange={handleChange} autoComplete="address-level1"/>
                        </FormGroup>
                    </div>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/student">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    )
};

export default StudentEdit;