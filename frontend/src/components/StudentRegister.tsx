import StudentList from "./StudentList";
import AddStudent from "./AddStudent";
import {Student} from "../model/Student";
import axios from "axios";
import {useEffect, useState} from "react";
import Search from "./Search";

export default function StudentRegister() {

    const [allStudents, setAllStudents] = useState<Student []>([])
    const [descriptionSearchQuery, setDescriptionSearchQuery] = useState<string>("")

    useEffect(() => {
        axios.get("/student")
            .then(result => setAllStudents(result.data))
    }, [])

    function handleSearchChange(searchQuery: string) {
        setDescriptionSearchQuery(searchQuery)
    }


    function addStudent(newStudentWithoutID: Student) {
        axios.post("/student", newStudentWithoutID)
            .then(savedStudents => {
                setAllStudents((prevAllStudents) => {
                    return [...prevAllStudents, savedStudents.data]
                })
            })

    }


    function deleteStudent(studentIDToDelete: string) {
        axios.delete("/student/"+ studentIDToDelete)
            .then(() => {
                const updatedStudentList = allStudents.filter((std) => {
                    if (std.id !== studentIDToDelete) {
                       return true
                    }

                })

                setAllStudents(updatedStudentList)
            })

    }



    const filteredStudents = allStudents.filter((std) => {
        if (std.name.toLowerCase().includes(descriptionSearchQuery.toLowerCase())){
            return true
        }

    })

    return (
        <section>
            <h1>Students Register</h1>
            <br/>
            <br/>
            <Search handleSearchChange={handleSearchChange}/>
            <br/>
            <StudentList students={filteredStudents} handleDeleteStudent={deleteStudent}/>
            <AddStudent handleAddStudent={addStudent}/>

        </section>
    )
}