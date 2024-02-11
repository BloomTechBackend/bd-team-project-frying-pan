const loginForm = document.querySelector("#login-form");

loginForm.onsubmit = async function(evt) { 
  evt.preventDefault();
  const username = document.querySelector("#usr").value;
  const password = document.querySelector("#psd").value;

  const account = {
    "username": username,
    "password": password
  }

  alert("username: " + username);

  alert("password: " + password);

  const json = JSON.stringify(account);
  const url = 'https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/account' + encodeURIComponent(username) + '/status';

  alert(url);
  axios.post(url, account)
  .then(response => {
    // Handle success here
    console.log('success: ',response.data);
    alert(response.data.status)
    window.location.reload()
  })
  .catch(error => {
    // Handle error here
    console.error("error : ", error);
    alert(error);
  });
  

  // try {
  //   const response = await axios.post(url, json, {
  //     headers: {
  //       'Content-Type': 'application/json',
  //     },
  //   });
  //   // Handle success
  //   console.log(response.data); 
  //   alert(response.data.status)
  //   window.location.reload()
  // } catch (error) {
  //   // Handle error
  //   console.error(error); 
  //   alert(error);
  // }



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
