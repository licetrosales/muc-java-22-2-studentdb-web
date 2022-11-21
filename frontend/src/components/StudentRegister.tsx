import StudentList from "./StudentList";
import AddStudent from "./AddStudent";
import {Student} from "../model/Student";
import axios from "axios";
import {useState} from "react";

export default function StudentRegister(){

    const [allStudents, setAllStudents] = useState<Student []>([])

    function addStudent(newStudentWithoutID: Student){
        axios.post("/student",newStudentWithoutID)
            .then(savedStudents => {
                setAllStudents((prevAllStudents) => {
                    return [...prevAllStudents, savedStudents.data]
                })
            })

    }

    return (
        <section>
            <h1>Students Register</h1>
            <br/>
            <br/>
            <StudentList students={allStudents}/>
            <AddStudent handleAddStudent={addStudent}/>
        </section>
    )
}