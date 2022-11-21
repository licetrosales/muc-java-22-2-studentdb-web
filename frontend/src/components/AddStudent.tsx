import {ChangeEvent, useState} from "react";
import {Student} from "../model/Student";

type AddStudentProps = {
handleAddStudent(student: Student):void
}

export default function AddStudent(props: AddStudentProps){
const [studentName, setStudentName] = useState<string>("");

function handleStudentNameChange(event: ChangeEvent<HTMLInputElement>) {
    setStudentName(event.target.value)
}

function handleAddStudent(){
    const newStudent: Student = {
        name : studentName
    }
    props.handleAddStudent(newStudent)
    }


 return (
     <section>
     <input onChange={handleStudentNameChange}/>
         <button onClick={handleAddStudent}>Add Student</button>
     </section>
 )
}