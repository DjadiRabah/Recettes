(function(params) {
	function upload(event) {
		cloudinary.openUploadWidget({
			cloud_name : 'dafkxhd7n',
			resource_type : "image",
			uploadPreset : "recipes_preset",
			multiple : "false",
			maxFiles : 1,
			type: "private"
		}, function(error, result) {
			if(result)
			{
				if(result.event === "success")
				{
					var split = result.info.url.split("/");
					window.location.href = window.location.href + "/" + split[6] + "/" + result.info.path;
				}
			}
		});
	}

	$("#imageUploader").click(upload);
})();
