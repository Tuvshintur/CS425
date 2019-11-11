import React from 'react'
import ReactDOM from 'react-dom'
const App = () => (
    <div className="FormField">
        <button onClick={login} className="FormField_Button">Sign In</button>
    </div>
)

function SignInService(type, userData) {
    return new Promise((resolve, reject) => {
        fetch('/auth/' + type,
            {
                method: 'POST',
                headers: new Headers({
                    'Content-Type': 'application/json'
                }),
                body: JSON.stringify(userData)
            })
            .then((response) => response.json())
            .then((responseJson) => {
                resolve(responseJson);
            })
            .catch((error) => {
                reject()
                console.error(error)
            })

    })
}

function login() {
    SignInService('signin', { username: "turuu", password: "urtpassword123" }).then((result) => {
        let responseJSON = result

        if (result.header.status === 'success') {
            sessionStorage.setItem('LoggedUserData', JSON.stringify(responseJSON))
            localStorage.setItem('LoggedUserData', JSON.stringify(responseJSON))
            console.log(responseJSON)
        }
        else {
            console.log(result.body.error.errorDesc)
        }
    })
}

ReactDOM.render(<App />, document.getElementById('root'))