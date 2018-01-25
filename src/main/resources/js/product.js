$(document).ready(function(){

  $('a.deleteLink').on('click', function(e){
    e.preventDefault();
    if(showMessage('Are you sure?')) {
      var that = this;
      $.ajax({
        type: 'POST',
        url: urls.product.delete,
        data: {
          id: $(that).parent('td').find('.id').val()
        },
        success: function(data) {
          $('div.message').text('Product deleted');
          $(that).closest('tr').remove();
        },
        error : function(e) {
          alert("Error!")
          console.log("ERROR: ", e);
        }
      });
    }
  });

  $('#searchclear').on('click', function(e) {
    $('input[name=searchData]').val("");
    $('input[name=searchData]').closest('form').submit();
  });

});

function showMessage(message) {

  return confirm(message);

}