document.addEventListener( 'DOMContentLoaded', function ()
{
    const listProduct = document.getElementById( 'list-product' );
    const formProduct = document.getElementById( 'form-fruit' ); // Make sure you have this ID on your form
    const name_cloth = document.getElementById( 'name_cloth' );
    const price_cloth = document.getElementById( 'price_cloth' );
    const mota = document.getElementById( 'mota' );
    const tinhtrang = document.getElementById( 'tinhtrang' );
    const image_cloth = document.getElementById( 'image_cloth' );
    const idFruit = document.getElementById( 'idFruit' );

    // Fetch and display products
    const fetchData = () =>
    {
        fetch( 'http://localhost:3000/api/product' )
            .then( res => res.json() )
            .then( data =>
            {
                displayFruit( data.data );
            } )
            .catch( err => console.error( err ) );
    };

    // Display products in the table
    const displayFruit = ( fruits ) =>
    {
        listProduct.innerHTML = fruits.map( fruit => `
            <tr>
                <td>${ fruit.name_cloth }</td>
                <td>${ fruit.price_cloth }</td>
                <td>${ fruit.mota }</td>
                <td>${ fruit.image_cloth.map( image => `<img src="${ image }" alt="Image of ${ fruit.name_cloth }" style="width: 100px;">` ).join( '' ) }</td>
                <td>${ fruit.brand }</td>
                <td>${ fruit.tinhtrang }</td>
                <td>
                    <button class="btn btn-warning btn-sm edit-btn" data-id="${ fruit._id }">Edit</button>
                    <button class="btn btn-danger btn-sm delete-btn" data-id="${ fruit._id }">Delete</button>
                </td>
            </tr>
        `).join( '' );
    };

    // Add or update product
    formProduct.addEventListener( 'submit', function ( event )
    {
        event.preventDefault();
        if ( idFruit.value === '' )
        {
            addFruit();
        } else
        {
            updateFruit();
        }
    } );

    // Add new product
    const addFruit = async () =>
    {
        const formData = new FormData( formProduct );
        try
        {
            const response = await fetch( 'http://localhost:3000/api/add-fruit-with-file-image', {
                method: 'POST',
                body: formData
            } );
            const result = await response.json();
            if ( result.status === 200 )
            {
                alert( 'Thêm mới thành công' );
                fetchData();
                formProduct.reset();
            } else
            {
                alert( 'Thêm mới thất bại' );
            }
        } catch ( error )
        {
            console.error( 'Error:', error );
            alert( 'Đã xảy ra lỗi' );
        }
    };

    // Update existing product
    const updateFruit = async () =>
    {
        const formData = new FormData( formProduct );
        try
        {
            const response = await fetch( `http://localhost:3000/api/update/${ idFruit.value }`, {
                method: 'PUT',
                body: formData
            } );
            const result = await response.json();
            if ( result.status === 200 )
            {
                alert( 'Cập nhật thành công' );
                fetchData();
                formProduct.reset();
            } else
            {
                alert( 'Cập nhật thất bại' );
            }
        } catch ( error )
        {
            console.error( 'Error:', error );
            alert( 'Đã xảy ra lỗi' );
        }
    };

    // Delete product
    listProduct.addEventListener( 'click', function ( event )
    {
        if ( event.target.classList.contains( 'delete-btn' ) )
        {
            const id = event.target.dataset.id;
            if ( confirm( 'Bạn có muốn xóa sản phẩm này không?' ) )
            {
                fetch( `http://localhost:3000/api/delete/${ id }`, {
                    method: 'DELETE'
                } )
                    .then( response =>
                    {
                        if ( response.status === 200 )
                        {
                            alert( 'Xóa thành công' );
                            fetchData();
                        } else
                        {
                            alert( 'Xóa thất bại' );
                        }
                    } )
                    .catch( error =>
                    {
                        console.error( 'Error:', error );
                        alert( 'Đã xảy ra lỗi khi xóa sản phẩm' );
                    } );
            }
        }
    } );

    // Initialize by fetching data
    fetchData();
} );
