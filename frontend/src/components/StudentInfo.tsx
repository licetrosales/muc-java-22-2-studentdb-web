
import {Student} from "../model/Student";

type StudentProps = {
   student : Student,
    handleDeleteStudent(id : string):void
}

export default function StudentInfo(props: StudentProps){

    function handleDeleteStudent(){
        if(props.student.id) {
            props.handleDeleteStudent(props.student.id)
        } else {
            console.error("ID gibt es nicht!")
        }
    }

    return (
        <section>
            <div>Student ID: {props.student.id} </div>
            <div>Student name: {props.student.name} </div>
            <br/>
            <button>Update 🔄</button>

            <button onClick={handleDeleteStudent}>Delete ✅</button>
        </section>
    )
}