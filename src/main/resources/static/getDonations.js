$(document).ready(function() {
    $('#donations').DataTable( {
        dom: 'lBfrtip',
        lengthMenu: [ [5,10, 25, 50, -1], [5,10, 25, 50, "All"] ],


    } );

} );