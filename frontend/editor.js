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
  const tablesContainer = document.getElementById('editorTables');

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

// toggle by clicking table data box
document.addEventListener('DOMContentLoaded', function () {
  const tablesContainer = document.getElementById('templateList');

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



