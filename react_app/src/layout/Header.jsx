import { Link } from "react-router-dom";

function Header() {

  return(
    <>
      <h1>Header Line</h1>
      <Link to="/">Home</Link> / 
      <Link to="/notice/list">Notice List</Link> / 

      <Link to="/member/login">Login</Link>

      <Link to="/study/param?num=10&name=winter">Study Param</Link> / 
      <Link to="/study/param/10/winter">Study Param2</Link> / 
      <Link to="/study/param" state={{age:10, user:"winter"}}>Study Param3</Link>
    </>
  )
}

export default Header;