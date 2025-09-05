import { useNavigate } from "react-router-dom";

export default function Login() {

  const nav = useNavigate();

  function login(e) {
    e.preventDefault();

    const form = new FormData(e.target);

    let all = Object.fromEntries(form.entries());
    console.log(all);

    fetch("http://localhost/api/member/login", {
      method: 'post',
      body: form
    })
      // .then(r => r.json())
      .then(r => {
        const header = r.headers;
        console.log(header.get("Accesstoken"));
        localStorage.setItem('Accesstoken', header.get("Accesstoken"));
        sessionStorage.setItem('Accesstoken', header.get("Accesstoken"));
        nav("/");
      })
      .catch(e => console.log(access))
      ;
  }

  return(
    <>
      <h1>Login page</h1>

      <form onSubmit={login}>
        <div>
          <input type="text" name="username"/>
          <input type="password" name="password" autoComplete="off"/>
          <button>Login</button>
        </div>
      </form>
    </>
  )
}