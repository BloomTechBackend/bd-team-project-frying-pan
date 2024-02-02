
// Open Tab function
function openTab(evt, tabName) {
  // Declare all variables
  let i, tabcontent, tablinks;

  // Get all elements with class="tabcontent" and hide them
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Get all elements with class="tablinks" and remove the class "active"
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  // Show the current tab, and add an "active" class to the button that opened the tab
  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " active";
}


// Toggle menu function
function toggleMenu(isLoggedIn) {
  document.getElementById('menu-before-login').style.display = isLoggedIn ? 'none' : 'block';
  document.getElementById('menu-after-login').style.display = isLoggedIn ? 'block' : 'none';
}


// Check if the user is logged in, then display signned accessible features 
window.onload = async function(evt) { 
  evt.preventDefault();

  const account = {
    "username": username
  }

  const json = JSON.stringify(account);
  const url = 'https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/' + Candy + '/status';
  

  axios.get('http://localhost:8080/contacts/')
  .then(function (response) {
    // handle success
    console.log(response);
    toggleMenu(response.data.status);
  })
  .catch(function(error) {
    toggleMenu(false);
    console.log(error);
  })

  // try {
  //   const response = await axios.get('https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/' + Candy + '/status');
  //   // Handle success
  //   console.log(response.data); 
  //   toggleMenu(response.data.status);
  //   alert(response.data.status)
  //   window.location.reload()
  // } catch (error) {
  //   // Handle 
  //   toggleMenu(response.data.false);
  // }
}