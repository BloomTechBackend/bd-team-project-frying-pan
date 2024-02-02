const templateForm = document.querySelector('#template-form'); // to submit to save or to generate test
const templateList = document.querySelector("#template"); // get template list from

const hiraganaIds = [
  ["h000", "h001", "h002", "h003", "h004"],
  ["h005", "h006", "h007", "h008", "h009"],
  ["h010", "h011", "h012", "h013", "h014"],
  ["h015", "h016", "h017", "h018", "h019"],
  ["h020", "h021", "h022", "h023", "h024"],
  ["h025", "h026", "h027", "h028", "h029"],
  ["h030", "h031", "h032", "h033", "h034"],
  ["h035", "h036", "h037"], 
  ["h038", "h039", "h040", "h041", "h042"],
  ["h043", "h044", "h045"]
];

const katakanaIds = [
  ["k000", "k001", "k002", "k003", "k004"],
  ["k005", "k006", "k007", "k008", "k009"],
  ["k010", "k011", "k012", "k013", "k014"],
  ["k015", "k016", "k017", "k018", "k019"],
  ["k020", "k021", "k022", "k023", "k024"],
  ["k025", "k026", "k027", "k028", "k029"],
  ["k030", "k031", "k032", "k033", "k034"],
  ["k035", "k036", "k037"], 
  ["k038", "k039", "k040", "k041", "k042"],
  ["k043", "k044", "k045"]
];

// https://rg894mwuek.execute-api.us-west-2.amazonaws.com/app

// Generic toggle all class matching checkboxes in the same table
function toggleAllCheckboxes(source) {
  let selector = '.' + source.className.split(' ').join('.');
  let parentTable = source.closest('table'); 
  let checkboxes = parentTable.querySelectorAll(selector);
  
  checkboxes.forEach(checkbox => {
      if (checkbox !== source) {
          checkbox.checked = source.checked;
      }
  });
}

// toggle by clicking table data box
document.addEventListener('DOMContentLoaded', function () {
  let parentTable = source.closest('table'); 
  const tablesContainer = document.getElementById('tablesContainer');

  tablesContainer.addEventListener('click', function (e) {
      // Check if the click is inside a cell
      if (e.target.tagName === 'TD') {
          const checkbox = e.target.querySelector('input[type="checkbox"]');
          if (checkbox) {
              checkbox.checked = !checkbox.checked;
          }
      } else if (e.target.tagName === 'INPUT' && e.target.type === 'checkbox') {
          // Prevent double toggling when the checkbox itself is clicked
          e.stopPropagation();
      }
  });
});


// Basic JS to html test
function sayHi() {
  alert("Hello");
}

// const x = document.getElementById("demo");
// x.innerHTML="Hello World!"

// const y = document.getElementById("title");
// y.innerHTML="Hello World!"


