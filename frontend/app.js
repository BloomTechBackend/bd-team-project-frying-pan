const loginForm = document.querySelector("#login-form");
const registerForm = document.querySelector("#register-form");
const deleteForm = document.querySelector("#delete-form");

const editorForm = document.querySelector("#editor-form");
const templateForm = document.querySelector("#template-form");
const templateList = document.querySelector("#templateList");
const testDoc = document.getElementById('testDoc');
const customTable = document.getElementById('customTable');

let currUsername = null;
let currTemplateId = null;

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

/* 
  Display functions
*/
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

/* 
  Account functions
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
        alert(response.data.errorMessage);
        console.log(response.data);
      } else {
        console.log('success: ',response.data);
        alert(response.data.logMessage);
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
        alert(response.data.errorMessage);
        console.log(response.data);
      } else {
        // Handle success here
        console.log('success: ',response.data);
        alert(response.data.logMessage)
        currUsername = response.data.username;
        loginForm.reset();
        afterLogin() 
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
        alert(response.data.errorMessage);
        console.log(response.data);
      } else {
        // Handle success here
        console.log('success: ',response.data);
        alert(response.data.logMessage)
        currUsername = null;
        afterLogOut();
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
      const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/account/${currUsername}`;
      await axios.delete(url, {data: account})
      .then(response => {
        if (response.data.errorType != null) {
          alert(response.data.errorMessage);
          console.log(response.data);
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

/* 
  Modal functions
*/ 
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  const genModal = document.getElementById('genTestModal');
  if (event.target == genModal) {
    genModal.style.display = "none";
  }
  const customModal = document.getElementById('customTermModal');
  if (event.target == customModal) {
    customModal.style.display = "none";
  }
  const createNewTemplateModal = document.getElementById('createNewTemplateModal');
  if (event.target == createNewTemplateModal) {
    createNewTemplateModal.style.display = "none";
  }
}

function openModal(modalId) {
  document.getElementById(modalId).style.display='block';
}

function closeModal(modalId) {
  document.getElementById(modalId).style.display='none';
}

/*
  Generate test functions
*/

// Generate Tests and website events
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
  if (title == null) {
    title == "";
  }

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

  let customTermBox = document.querySelectorAll("input[name='customTermId']");
  // Map over the NodeList to extract values
  let customTermIds = Array.from(customTermBox).map(box => box.value);

  // function to get custom Terms by Id -- or we've stored them 
  let isRandomHiragana= document.getElementById('isHiraganaRandom').checked;
  let isRandomKatakana = document.getElementById('isKatakanaRandom').checked;
  let isRandomCustom = document.getElementById('isCustomRandom').checked;

  const request = {
    "hiraganaIds": selectedHiraganaIds,
    "katakanaIds": selectedKatakanaIds,
    "customIds": customTermIds,
    "isHiraganaRandom": isRandomHiragana,
    "isKatakanaRandom": isRandomKatakana,
    "isCustomRandom": isRandomCustom,
    "title": title
  }

  console.log('request', request);
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/test`;
    await axios.post(url, request)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage);
        console.log(response.data);
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
    "isHiraganaRandom": true,
    "isKatakanaRandom": true,
    "isCustomRandom": true,
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

/*
  Custom Term functions
*/

// Add Custom Term Actions
async function addCustomTerm() {
  closeModal('customTermModal');
  createCustomTerm();
  document.getElementById("custom-term-form").reset();
}

// Create Term Request
async function createCustomTerm() {
  const customTermForm = document.getElementById('custom-term-form');
  const romanization = customTermForm.querySelector('#romanization').value;
  const symbol = customTermForm.querySelector('#symbol').value;
  
  const customTerm = {
    "romanization": romanization,
    "symbol": symbol,
    "username": currUsername,
    "templateId": currTemplateId
  }

  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/term`;
    await axios.post(url, customTerm)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage)
        console.log(response.data);
      } else {
        // Handle success here
        console.log('success: ',response.data);
        alert('Success: term created');
        getCustomTermsByTemplateId();
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

// Delete Term Request
async function deleteCustomTerm() {
  const termId = document.querySelector('input[name="customTermId"]:checked').value;
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/term/${termId}`;
    await axios.delete(url)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage)
        console.log(response.data);
      } else {
        // Handle success here
        console.log('success: ',response.data);
        alert('Success: term deleted');
        getCustomTermsByTemplateId();
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

function uncheckCustomTerm() {
  const radio = document.querySelector('input[name="customTermId"]:checked');
  if (radio !== null) {
    radio.checked = false;
  }
}

// Get custom terms by Template Id and Date Created Request
async function getCustomTermsByTemplateId() {
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/term/templateId?templateId=${currTemplateId}`;
    await axios.get(url)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage)
      } else {
        // Handle success here
        console.log('success: ',response.data);
        // call populate test tab 
        populateCustomTerms(response.data);
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

// Static data example
async function getCustomTermsByStaticTemplateId() {
  try {
    const templateId = 'zekrZy'; // Example username
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/term/templateId?templateId=${templateId}`;

    axios.get(url)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage)
        console.log(response.data);
      } else {
        // Handle success here
        console.log('success: ',response.data);

        // call populate test tab 
        populateCustomTerms(response.data);
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
 
// Display custom terms
function populateCustomTerms(customTermsData) {
  console.log('loading custom terms');

  const customTermsContainer = customTable.querySelector('.customTermsContainer');
  customTermsContainer.innerHTML = '';

  const terms = customTermsData.terms;
  if (terms == null) {
    terms = [];
  }
  terms.forEach(term => {
    let tr = document.createElement("tr");
    let td = document.createElement("td");
    let spanQuestion = document.createElement("span");
    let spanAnswer = document.createElement("span");
    let label = document.createElement("label");
    
    // Create text nodes
    let romanizationText = document.createTextNode(term.romanization); 
    let symbolText = document.createTextNode(term.symbol); 

    // Create radio button
    let radioButton = document.createElement("input");
    radioButton.setAttribute("type", "radio");
    radioButton.setAttribute("name", "customTermId"); // Ensure all radio buttons share the same 'name'
    radioButton.setAttribute("value", term.termId);

    spanQuestion.appendChild(romanizationText);
    spanAnswer.appendChild(symbolText);

    spanQuestion.setAttribute("class", "romaji");
    spanAnswer.setAttribute("class", "symbol");

    // used to delete later
    tr.setAttribute('id', term.termId);

    // Append elements
    label.appendChild(radioButton); 
    label.appendChild(spanQuestion);
    label.appendChild(spanAnswer);

    td.appendChild(label);
    tr.appendChild(td);
    customTermsContainer.appendChild(tr);
  });
}
/*
  Template methods
*/
// Create new template and display templates in alphabetical order
function addNewTemplate() {
  closeModal('createNewTemplateModal');
  createNewTemplate();
}

// Create New Template Request 
async function createNewTemplate() {
  const title = document.querySelector('#templateTitle').value;
  try {
    template = {
      "title": title,
      "username": currUsername,
      "hiraganaIdList": [],
      "katakanaIdList": []
    }
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/test_template`;
    axios.post(url, template)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage);
        console.log(response.data);
      } else {
        // Handle success here
        console.log('success New Template:', response.data);
        getTemplatesByUsernameTitle();
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

// get template data and load into editor form
async function openTemplate() {
  await getTemplate();
  getCustomTermsByTemplateId();
  document.getElementById("editorBTN").click();
}

// Get Template Request 
async function getTemplate() {
  const templateId = document.querySelector("input[name='templateSelector']:checked").value;
  currTemplateId = templateId;
  console.log('request: ',  templateId);
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/test_template/${currTemplateId}`;
    await axios.get(url)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage);
        console.log(response.data);
      } else {
        // Handle success here
        console.log('success: ', response.data);
        loadTemplate(response.data)
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

// loads the template data into the editor
function loadTemplate(templateData) {
  const template = templateData.template; // the response contains a template model object
  editorForm.reset();
  // // set title
  document.querySelector('#title').value = template.title;

  // display hiragana
  const hiraganaIdList = template?.hiraganaIdList || [];
  hiraganaIdList.forEach(hiraganaId => {
    let checkbox = document.querySelector(`input[type="checkbox"][value="${hiraganaId}"]`);
    if (checkbox) {
      checkbox.checked = true;
    }
  })

  // display display katakana
  const katakanaIdList = template?.katakanaIdList || [];
  katakanaIdList.forEach(katakanaId => {
    let checkbox = document.querySelector(`input[type="checkbox"][value="${katakanaId}"]`);
    if (checkbox) {
      checkbox.checked = true;
    }
  })
}

// Delete Template Request 
async function deleteTemplate() {
  const templateId = document.querySelector("input[name='templateSelector']:checked").value;
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/test_template/${templateId}`;
    await axios.delete(url)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage);
        console.log(response.data);
      } else {
        // Handle success here
        console.log('success delete: ', response.data);
        getTemplatesByUsernameTitle();
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

// saves and closes current template
function closeTemplate() {
  updateTemplate();
  const customTermsContainer = customTable.querySelector('.customTermsContainer');
  customTermsContainer.innerHTML = '';
  editorForm.reset();
  currTemplateId=null;
  document.getElementById("templatesBTN").click();
}

// Update Template Request 
async function updateTemplate() {
  const templateId = document.querySelector("input[name='templateSelector']:checked").value;
  const title = document.querySelector('#title').value;
  
  // Select all checked checkboxes with both 'hiragana' and 'term' classes
  const hiraganaBoxes = document.querySelectorAll('.hiragana.term:checked');
  const katakanaBoxes = document.querySelectorAll('.katakana.term:checked');

  // Map over the NodeList to extract values
  const hiraganaValues = Array.from(hiraganaBoxes).map(box => box.value);
  const katakanaValues = Array.from(katakanaBoxes).map(box => box.value);

  const template = {
    "templateId": currTemplateId,
    "title": title,
    "username": currUsername, 
    "hiraganaIdList": hiraganaValues,
    "katakanaIdList": katakanaValues
  }
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/test_template/${templateId}`;
    await axios.put(url, template)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage);
        console.log(response.data);
      } else {
        // Handle success here
        console.log('success save: ', response.data);
        alert("Successful Save");
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

async function getTemplatesByUsernameTitle() {
  console.log('getting Templates by Username...');
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/test_template?username=${currUsername}`;
    await axios.get(url)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage);
        console.log(response.data);
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

// Get static data templates's by username and title
async function getTemplateStatic() {
  currUsername = "username";
  try {
    const url = `https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app/test_template?username=${currUsername}`;
    await axios.get(url)
    .then(response => {
      if (response.data.errorType != null) {
        alert(response.data.errorMessage);
        console.log(response.data);
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

// display templates in a table
async function populateTemplateList(templateData){
  console.log('loading test template table');
  const templateContainer = templateList.querySelector('.templateContainer');
  templateContainer.innerHTML = '';

  const templates = templateData.templates;

  templates.forEach(template => {
    let tr = document.createElement("tr");
    let td = document.createElement("td");
    let spanTitle = document.createElement("span");
    let label = document.createElement("label");

    // Create text nodes
    let titleText = document.createTextNode(template.title); 

    // Create radio button
    let radioButton = document.createElement("input");
    radioButton.setAttribute("type", "radio");
    radioButton.setAttribute("name", "templateSelector"); // Ensure all radio buttons share the same 'name'
    radioButton.setAttribute("value", template.templateId);

    // Set 'id' attribute to the string value, not a text node
    tr.setAttribute('id', template.templateId);

    // Append elements to the table cell
    spanTitle.appendChild(titleText);
    label.appendChild(radioButton);
    label.appendChild(spanTitle);
    td.appendChild(label); 

    // Append the cell to the row, and the row to the list
    tr.appendChild(td);
    templateContainer.appendChild(tr);
  });
}

/*
After logging in we need to

1. Change the navigator tabs
2. Account displays the username 
3. Templates displays the user's saved templates
4. Editor displays the additional features and custom terms tabe
*/

// After Login calls: 
async function afterLogin() {
  toggleDisplayOnLogin();
  toggleAccountUsername(); 
  document.getElementById("accountBTN").click();
  getTemplatesByUsernameTitle();
} 

// Log Out reverts back to before Login: 
async function afterLogOut() {
  toggleDisplayOnLogin();
  toggleAccountUsername(); 
  document.getElementById("loginBTN").click();
  templateList.querySelector('.templateContainer').innerHTML = '';
}

// Toggle before and after login display
function toggleDisplayOnLogin() {
  const before = document.querySelectorAll('.beforeLogin');
  before.forEach(element => {
    element.style.display = currUsername ? 'none' : 'block';
  });
  const after = document.querySelectorAll('.afterLogin');
  after.forEach(element => {
    element.style.display = currUsername ? 'block' : 'none';
  });
}

// Toggle Account Username Display
function toggleAccountUsername() {
  const accountBtn = document.getElementById('accountBTN');
  if (currUsername) {
    accountBtn.textContent = "Account: " + currUsername;
  } else {
    accountBtn.textContent = "Account"
  }
}

// function toggleCurUsername() {
//   if (currUsername) {
//     currUsername = null; 
//     afterLogOut()
//   } else {
//     currUsername = "username";
//     afterLogin() 
//   }
// }