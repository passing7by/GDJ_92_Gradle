import { useRef, useState } from "react";

function Add() {
  
  // const [name, setName] = useState("test");
  
  // const n = useRef("아무거나");

  // function get() {
  //   console.log(add);
  // }
  
  // name에 들어가는 타입은 객체 혹은 배열이 될 수도 있음
  // const [add, setAdd] = useState({
  //   boardTitle: "",
  //   boardWriter: "",
  //   boardContents: ""
  // });

  // function inputChange(e) {
  //   setAdd((prevState) => ({
  //     ...prevState,
  //     [e.target.name]:e.target.value
  //   }))
  // }

  // add 버튼 클릭하면 ref 업데이트
  const [title, setTitle] = useState("")
  const [writer, setWriter] = useState("")
  const [contents, setContents] = useState("")

  const boardTitle = useRef("아무거나");
  const boardWriter = useRef("아무거나");
  const boardContents = useRef("아무거나");
  
  function insert() {
    let param = new URLSearchParams();
    param.append("boardTitle", boardTitle.current.value);
    param.append("boardWriter", boardWriter.current.value);
    param.append("boardContents", boardContents.current.value);

    fetch('http://localhost/notice/add', {
      method: 'post',
      body: param
    })
    .then(r => r.json())
    .then(r => {
      console.log(r);

      // setTitle(boardTitle.current.value);
      // setWriter(boardWriter.current.value);
      // setContents(boardContents.current.value);

      // boardTitle.current.value = "";
      // boardWriter.current.value = "";
      // boardContents.current.value = "";
    })
    ;
  }

  // function add(e) {
  //   e.preventDefault();
  //   const formData = new FormData(e.target); // <form></form> , e.target로 폼 객체 자체를 담아 줌
  // }

  return (
    <>
      <h1>Add Page</h1>
      {/* <h3>Input: {name}</h3> */}

      {/* ref={n}: input 태그 전체가 n과 연결됨 */}
      {/* <form onSubmit={add}> */}
        <input type="text" ref={boardTitle}/>
        <input type="text" ref={boardWriter} />
        <textarea ref={boardContents} id=""></textarea>
        {/* <input type="text" name="boardTitle" onChange={inputChange}/>
        <input type="text" name="boardWriter" onChange={inputChange} />
        <textarea name="boardContents" onChange={inputChange} id=""></textarea> */}

        {/* <button onClick={get}>click</button> */}
        <button onClick={insert}>insert</button>

        <h3>Add 내용</h3>
        <p>boardTitle: {title}</p>
        <p>boardWriter: {writer}</p>
        <p>boardContents: {contents}</p>
        {/* <button>Use Form</button> */}
      {/* </form> */}
    </>
  )
}

export default Add;