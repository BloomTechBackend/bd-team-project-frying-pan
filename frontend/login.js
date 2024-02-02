const loginForm = document.querySelector("#login-form");

loginForm.onsubmit = async function(evt) { 
  evt.preventDefault();
  const username = loginForm.querySelector("#usr").value;
  const password = loginForm.querySelector("#psd").value;

  const account = {
    "username": username,
    "password": password
  }

  const json = JSON.stringify(account);
  const url = 'https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/' + username + '/status';
  
  try {
    const response = await axios.post(url, json, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    // Handle success
    console.log(response.data); 
    alert(response.data.status)
    window.location.reload()
  } catch (error) {
    // Handle error
    console.error(error); 
    alert(error);
  }
  // axios.post('https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/account/' + username + '/status', account)
  // .then(function (response) {
  //   // handle success
  //   console.log(response);
  //   alert(response.data.status)
  //   window.location.reload()
  // })
  // .catch(function (error) {
  //   // Handle error
  //   console.error('Error:', error);
  //   alert(error.data);
  // });

}
