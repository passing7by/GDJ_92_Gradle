import { Link } from "react-router-dom";

function Header() {

  return(
    <>
      <h1>Header Line</h1>
      <Link to="/">Home</Link> / 
      <Link to="/notice/list">Notice List</Link> / 


      <Link to="/study/param?num=10&name=winter">Study Param</Link> / 
      <Link to="/study/param/10/winter">Study Param2</Link> / 
      <Link to="/study/param" state={{age:10, user:"winter"}}>Study Param3</Link>

      {/* 액세스토큰 없을 때 보여주기 */}
      <div>
        <Link to="/member/login">Login</Link>
        <Link to="/member/join">Join</Link>
      </div>
      {/* 액세스토큰 있을 때 보여주기 */}
      <div>
        <Link to="/member/logout">Logout</Link>
        <Link to="/member/mypage">MyPage</Link>
      </div>
    </>
  )
}

export default Header;