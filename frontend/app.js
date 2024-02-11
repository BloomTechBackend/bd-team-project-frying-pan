const loginForm = document.querySelector("#login-form");
const registerForm = document.querySelector("#register-form");
const deleteForm = document.querySelector("#delete-form");

const editorForm = document.querySelector("#editor-form");
const templateForm = document.querySelector("#template-form");
const templateList = document.querySelector("#templateList");
const testDoc = document.getElementById('testDoc');


let currUsername = null;
let currTemplateId = null;

document.getElementById("editorBTN").click();
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

// Toggle login layouut and features - check if menu needs period or not
function toggleVisibilityOnLogin(isLoggedIn) {
  const before = document.querySelectorAll('.beforeLogin')
  before.forEach(element => {
    element.style.display = isLoggedIn ? 'none' : 'block';
  });

  const after = document.querySelectorAll('.afterLogin')
  after.forEach(element => {
    element.style.display = isLoggedIn ? 'block' : 'none';
  });
}

// there is a change that it's many items that need to be toggled
/*
function toggleLoggedIn(isLoggedIn) {
  document.getElementsByClassName('.beforeLogin').style.display = isLoggedIn ? 'none' : 'block';
  document.getElementsByClassName('.afterLogin').style.display = isLoggedIn ? 'block' : 'none';
}
*/

// get accouunt status 
function isLoggedIn(username) {
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/account/${username}/status`;

    axios.get(url)
    .then(response => {
      // Handle success here
      console.log('success: ',response.data);
      return(response.data.isLoggedIn);
    })
  } catch(error) {
    return false;
  }
}

// example object format 
const staticAccount = {
  username: "Candy",
  password: "Apples"  
}

// Register Account Form
async function registerAccount(){
  let username = registerForm.querySelector('#username').value;
  let password = registerForm.querySelector('#password').value;
  let passwordConfirm = registerForm.querySelector('#passwordConfirm').value;
  
  const account = {
    "username": username,
    "password": password,
    "passwordConfirm": passwordConfirm
  }
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/account`;
    axios.post(url, account)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage)
      } else {
        console.log('success: ',response.data);
        alert(response.data.logMessage)
        registerForm.reset();
        openLogin();
      }
    })
  } catch(error) {
    if (error.response) {
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error.response.headers);
    } else if (error.request) {
      console.log(error.request);
    } else {
      console.log('Error', error.message);
    }
    alert(error.data)
    console.log(error.config);
  };  

}

function openLogin() {
  document.getElementById("loginBTN").click();
}

// Login Account Form 
async function loginAccount(){
  let username = loginForm.username.value;
  let password = loginForm.password.value;
  
  const account = {
    "username": username,
    "password": password
  }
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/account/${username}/status`;
    axios.post(url, account)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage)
      } else {
        // Handle success here
        console.log('success: ',response.data);
        alert(response.data.logMessage)
        currUsername = response.data.username;
        loginForm.reset();
        toggleVisibilityOnLogin(true);
        document.getElementById("accountBTN").click();
        getTemplatesByUsernameTitle();
      }
    })
  } catch(error) {
    if (error.response) {
      // The request was made and the server responded with a status code
      // that falls out of the range of 2xx
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error.response.headers);
    } else if (error.request) {
      // The request was made but no response was received
      // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
      // http.ClientRequest in node.js
      console.log(error.request);
    } else {
      // Something happened in setting up the request that triggered an Error
      console.log('Error', error.message);
    }
    alert(error);
    console.log(error.config);
  };  

}

// log out Account button
async function logOutAccount() {
  let username = currUsername;
  
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/account/${username}/status`;
    axios.put(url)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage)
      } else {
        // Handle success here
        console.log('success: ',response.data);
        alert(response.data.logMessage)
        currUsername = null;
        openLogin();
        toggleVisibilityOnLogin(false);
      }
    })
  } catch(error) {
    if (error.response) {
      // The request was made and the server responded with a status code
      // that falls out of the range of 2xx
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error.response.headers);
    } else if (error.request) {
      // The request was made but no response was received
      // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
      // http.ClientRequest in node.js
      console.log(error.request);
    } else {
      // Something happened in setting up the request that triggered an Error
      console.log('Error', error.message);
    }
    alert(error);
    console.log(error.config);
  }; 
}

// Delete Account
async function deleteAccount() {
    let password = deleteForm.querySelector('#passcode').value;
    
    const account = {
      "username": currUsername,
      "password": password
    }
    try {
      const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/account/${username}`;
      await axios.delete(url, { data: account })
      .then(response => {
        if (response.data.errorType != null) {
          alert(response.data.errorMessage)
        } else {
          // Handle success here
          console.log('success: ',response.data);
          alert(response.data.logMessage)
          currUsername = null;
          openLogin();
          toggleVisibilityOnLogin(false);
        }
      })
    } catch(error) {
      if (error.response) {
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      } else if (error.request) {
        // The request was made but no response was received
        // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
        // http.ClientRequest in node.js
        console.log(error.request);
      } else {
        // Something happened in setting up the request that triggered an Error
        console.log('Error', error.message);
      }
      alert(error);
      console.log(error.config);
    };  
}

// Arrays of ids 
const hiraganaIds = { 
  "h0": ["h000", "h001", "h002", "h003", "h004"],
  "h1": ["h005", "h006", "h007", "h008", "h009"],
  "h2":  ["h010", "h011", "h012", "h013", "h014"],
  "h3":  ["h015", "h016", "h017", "h018", "h019"],
  "h4":  ["h020", "h021", "h022", "h023", "h024"],
  "h5":  ["h025", "h026", "h027", "h028", "h029"],
  "h6":  ["h030", "h031", "h032", "h033", "h034"],
  "h7":  ["h035", "h036", "h037"], 
  "h8":  ["h038", "h039", "h040", "h041", "h042"],
  "h9":  ["h043", "h044", "h045"],
  "h10": ["h046", "h047", "h048", "h049", "h050"],
  "h11": ["h051", "h052", "h053", "h054", "h055"],
  "h12": ["h056", "h057", "h058", "h059", "h060"],
  "h13": ["h061", "h062", "h063", "h064", "h065"],
  "h14": ["h066", "h067", "h068", "h069", "h070"],
  "h15": ["h071", "h072", "h073"],
  "h16": ["h074", "h075", "h076"],
  "h17": ["h077", "h078", "h079"],
  "h18": ["h080", "h081", "h082"],
  "h19": ["h083", "h084", "h085"],
  "h20": ["h086", "h087", "h088"],
  "h21": ["h089", "h090", "h091"],
  "h22": ["h092", "h093", "h094"],
  "h23": ["h095", "h096", "h097"],
  "h24": ["h098", "h099", "h100"],
  "h25": ["h101", "h102", "h103"],
  "h26": ["h104", "h105", "h106"]
};

const katakanaIds = {
  "k0": ["k000", "k001", "k002", "k003", "k004"],
  "k1": ["k005", "k006", "k007", "k008", "k009"],
  "k2":  ["k010", "k011", "k012", "k013", "k014"],
  "k3":  ["k015", "k016", "k017", "k018", "k019"],
  "k4":  ["k020", "k021", "k022", "k023", "k024"],
  "k5":  ["k025", "k026", "k027", "k028", "k029"],
  "k6":  ["k030", "k031", "k032", "k033", "k034"],
  "k7":  ["k035", "k036", "k037"], 
  "k8":  ["k038", "k039", "k040", "k041", "k042"],
  "k9":  ["k043", "k044", "k045"],
  "k10": ["k046", "k047", "k048", "k049", "k050"],
  "k11": ["k051", "k052", "k053", "k054", "k055"],
  "k12": ["k056", "k057", "k058", "k059", "k060"],
  "k13": ["k061", "k062", "k063", "k064", "k065"],
  "k14": ["k066", "k067", "k068", "k069", "k070"],
  "k15": ["k071", "k072", "k073"],
  "k16": ["k074", "k075", "k076"],
  "k17": ["k077", "k078", "k079"],
  "k18": ["k080", "k081", "k082"],
  "k19": ["k083", "k084", "k085"],
  "k20": ["k086", "k087", "k088"],
  "k21": ["k089", "k090", "k091"],
  "k22": ["k092", "k093", "k094"],
  "k23": ["k095", "k096", "k097"],
  "k24": ["k098", "k099", "k100"],
  "k25": ["k101", "k102", "k103"],
  "k26": ["k104", "k105", "k106"]
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  let genModal = document.getElementById('genTestModal');
  if (event.target == genModal) {
    genModal.style.display = "none";
  }

  let customModal = document.getElementById('customTermModal');
  if (event.target == customModal) {
    customModal.style.display = "none";
  }
}

function openModal(modalId) {
  document.getElementById(modalId).style.display='block';
}

function closeModal(modalId) {
  document.getElementById(modalId).style.display='none';
}

function generateTest() {
  closeModal('genTestModal');
  document.getElementById("testTitle").innerHTML = '';
  document.getElementById("testQuestions").innerHTML = '';
  document.getElementById("testAnswers").innerHTML = '';
  createTest();
}

// Create Test from editorForm
async function createTest() {
  
  let title = editorForm.querySelector('#title').value;

  let checkedHiraganaBox = document.querySelectorAll('input.hiragana.term[type="checkbox"]:checked');
  // Lookup string codes for each checked checkbox
  let selectedHiraganaIds = Array.from(checkedHiraganaBox).flatMap(function(checkbox) {
    let checkboxValue = checkbox.value;
    return hiraganaIds[checkboxValue] || []; // Returns the list of string codes or an empty array if not found
  });

  let checkedKatakanaBox = document.querySelectorAll('input.katakana.term[type="checkbox"]:checked');
  // Lookup string codes for each checked checkbox
  let selectedKatakanaIds = Array.from(checkedKatakanaBox).flatMap(function(checkbox) {
    let checkboxValue = checkbox.value;
    return katakanaIds[checkboxValue] || []; // Returns the list of string codes or an empty array if not found
  });

  // function to get custom Terms by Id -- or we've stored them 
  let customTermIds = [];
  let isRandomHiragana= document.getElementById('isHiraganaRandom').checked;
  let isRandomKatakana = document.getElementById('isKatakanaRandom').checked;;
  let isRandomCustom = document.getElementById('isCustomRandom').checked;;

  request = {
    "hiraganaIds": selectedHiraganaIds,
    "katakanaIds": selectedKatakanaIds,
    "customIds": customTermIds,
    "isRandomHiragana": isRandomHiragana,
    "isRandomKatakana": isRandomKatakana,
    "isRandomCustom": isRandomCustom,
    "title": title
  }

  console.log('request', request);
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/test/`;
    await axios.post(url, request)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage)
      } else {
        // Handle success here
        console.log('success: ',response.data);
        // call populate test tab 
        populateTestTab(response.data);
        document.getElementById('testBTN').click();
      }
    })
  } catch(error) {
    if (error.response) {
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error.response.headers);
    } else if (error.request) {
      // The request was made but no response was received
      // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
      // http.ClientRequest in node.js
      console.log(error.request);
    } else {
      // Something happened in setting up the request that triggered an Error
      console.log('Error', error.message);
    }
    alert(error);
    console.log(error.config);
  };  

}

async function testTemplate1() {
  alert("hello");
  request = {
    "hiraganaIds": ["h000", "h001", "h002", "h003", "h004"],
    "katakanaIds": ["h000", "h001", "h002", "h003", "h004"],
    "customIds": ["h000", "h001", "h002", "h003", "h004"],
    "isRandomHiragana": true,
    "isRandomKatakana": true,
    "isRandomCustom": true,
    "title": "hello world"
  }

  console.log('request', request);
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/test/`;
    await axios.post(url, request)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage)
      } else {
        // Handle success here
        console.log('success: ',response.data);
        // call populate test tab 
        populateTestTab(response.data);
        document.getElementById('testBTN').click();
      }
    })
  } catch(error) {
    if (error.response) {
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error.response.headers);
    } else if (error.request) {
      // The request was made but no response was received
      // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
      // http.ClientRequest in node.js
      console.log(error.request);
    } else {
      // Something happened in setting up the request that triggered an Error
      console.log('Error', error.message);
    }
    alert(error);
    console.log(error.config);
  };  
}

// populate TestDoc with TestData
function populateTestTab(testData) {
  const hiraganaModel = testData.hiraganaTest;
  const katakanaModel = testData.katakanaTest;
  const customModel = testData.customTest;

  const testTitle = document.getElementById("testTitle");
  const title = document.createElement("h2");
  
  const titleText = document.createTextNode(testData.title);
  title.appendChild(titleText);
  testTitle.append(title);

  if (hiraganaModel != null) {
    populateTestModel(hiraganaModel);
  }

  if (katakanaModel!= null) {
    populateTestModel(katakanaModel);
  }

  if (customModel!= null) {
    populateTestModel(customModel);
  }
}

// create Test Content elements from TestModels 
function populateTestModel(testModel) {
  const testQuestionsDiv = document.getElementById("testQuestions");
  const testAnswersDiv = document.getElementById("testAnswers");

  const testQuestionSection = document.createElement("div");
  const testAnswerSection = document.createElement("div");

  const questionsPre = document.createElement("pre");
  const answersPre = document.createElement("pre");

  const questionsTitle = document.createElement("h3");
  const answersTitle = document.createElement("h3");

  const questionTitleTest = document.createTextNode(testModel.title);
  const answerTitleTest = document.createTextNode(testModel.title);
  const questionsText = document.createTextNode(testModel.testQuestions);
  const answersText = document.createTextNode(testModel.testAnswers);

  questionsPre.setAttribute('class', "questions");
  answersPre.setAttribute('class', "answers");
  
  // append the text
  questionsTitle.appendChild(answerTitleTest);
  answersTitle.appendChild(questionTitleTest);
  questionsPre.appendChild(questionsText);
  answersPre.appendChild(answersText);

  // append the elements to div
  testQuestionSection.appendChild(questionsTitle);
  testQuestionSection.appendChild(questionsPre);

  testAnswerSection.appendChild(answersTitle);
  testAnswerSection.appendChild(answersPre);

  // append the div to the div
  testQuestionsDiv.appendChild(testQuestionSection);
  testAnswersDiv.appendChild(testAnswerSection);
}

// Toggle Test Questions and Answers
function toggleTest(event) {
  let questions = document.getElementById("testQuestions");
  let answers = document.getElementById("testAnswers");
  
  let answersDisplay = window.getComputedStyle(answers).display;

  // Check the current display state of the answers and toggle accordingly
  if (answersDisplay === "none") {
    answers.style.display = "flex"; // Show answers
    questions.style.display = "none"; // Hide questions
    event.textContent = "Show Questions"; // Change button text
    event.style.backgroundColor = "palegreen";
  } else {
    answers.style.display = "none"; // Hide answers
    questions.style.display = "flex"; // Show questions
    event.textContent = "Show Answers"; // Change button text
    event.style.backgroundColor = "";

  }
}

// document.getElementById("toggleTestButton").addEventListener("click", function() {
//   let questions = document.getElementById("testQuestions");
//   let answers = document.getElementById("testAnswers");
  
//   // Check the current display state of the answers and toggle accordingly
//   if (answers.style.display === "none") {
//     answers.style.display = "flex"; // Show answers
//     questions.style.display = "none"; // Hide questions
//     this.textContent = "Show Questions"; // Change button text
//   } else {
//     answers.style.display = "none"; // Hide answers
//     questions.style.display = "flex"; // Show questions
//     this.textContent = "Show Answers"; // Change button text
//   }
// });

async function createCustomTerm() {

}

async function deleteCustomTerm() {

}

async function getCustomTermsByTemplateId() {

}

async function populateCustomTerms() {

}


/*
// Query all checked checkboxes with class "hiragana term"
let checkedHiraganaBoxes = document.querySelectorAll('input.hiragana.term[type="checkbox"]:checked');

// To process the checked checkboxes, you can iterate over the NodeList
let checkedValues = Array.from(checkedHiraganaBoxes).map(function(checkbox) {
    return checkbox.value; // Retrieves the value attribute of each checked checkbox
});

return checkedValues; 
*/


// 
function getTemplateById() {
  try {
    const templateId = '000001'; // Example username
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/test_template/${templateId}`;

    axios.get(url)
    .then(response => {
      // Handle success here
      console.log('success: ',response.data);
    })
  } catch(error) {
    console.log('Error', error.message);
    console.log(error.config);
  };  
}

/*
After logging in we need to

1. change the menu from editor test register and login to editor test templates and accounts
2. Account needs to be populated with the username 
3. templates if they exist needs to be populated
4. Editor needs display custom terms when openning new templates
5. editor needs allow creating custom terms from a new template 

*/


// login Account 

async function afterLogin() {
  
} 

// adds username to account button
function addNametoAccount() {
  const accountTab = document.getElementById('accountBTN');
  let text = ": " + currUsername;
  let span = document.createElement("span");

  span.appendChild(text);
  accountTab.append(span);
}

// removes username from account button
function removeNameFromAccount() {
  // Assuming the span is the last child of the accountTab element
  let accountTab = document.getElementById('accountBTN');
  if (accountTab.lastChild.tagName === 'SPAN') {
      accountTab.removeChild(accountTab.lastChild);
  }
}

async function afterLogouut() {
  
}

function populateCustomTerms() {

}

// 2. get the user's templates and display them
async function getTemplatesByUsernameTitle() {
  try {
    const username = {
      "username": currUsername
    }
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/test_template/?username=${currUsername}`;
    await axios.get(url, username)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage)
      } else {
        // Handle success here
        console.log('success: ', response.data);
        // call populate template tab 
        populateTemplateList(response.data);
      }
    })
  } catch(error) {
    if (error.response) {
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error.response.headers);
    } else if (error.request) {
      // The request was made but no response was received
      // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
      // http.ClientRequest in node.js
      console.log(error.request);
    } else {
      // Something happened in setting up the request that triggered an Error
      console.log('Error', error.message);
    }
    alert(error);
    console.log(error.config);
  };  
}

// helper list of template to display

async function populateTemplateList(templateData){
  const templates = templateData.templates;

  templates.forEach(template => {
    let tr = document.createElement("tr");
    let td = document.createElement("td");
    let p = document.createElement("p");
    
    // Create text nodes
    let templateId = document.createTextNode(template.templateId);
    let title = document.createTextNode(template.title); 

    // Create radio button
    let radioButton = document.createElement("input");
    radioButton.setAttribute("type", "radio");
    radioButton.setAttribute("name", "templateSelection"); // Ensure all radio buttons share the same 'name'
    radioButton.setAttribute("value", template.templateId);

    // Set 'id' attribute to the string value, not a text node
    td.setAttribute('id', template.templateId);

    // Append elements to the table cell
    td.appendChild(radioButton); 
    p.appendChild(title);
    td.appendChild(p);

    // Append the cell to the row, and the row to the list
    tr.appendChild(td);
    templateList.appendChild(tr);
  });
}


// post template - no id
async function createTemplate() {

} 

// get template by Id
async function openTemplate() {

}


// put templateId - by id
async function saveTemplate() {

} 

// save one more time, remove the curr template Id and reset the editor-form
function closeTemplate() {
  currTemplateId=null;
  editorForm.reset();
} 


// post Term
async function createCustomTerm() {

} 

// deleteTerm
async function deleteCustomTerm() {

}

// get Term By TemplateId

async function getTermsByTemplateId() {

}

