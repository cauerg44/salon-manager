import { InputMask } from "@react-input/mask";

export default function FormInput(props: any) {

  const {
    validation,
    invalid = "false",
    dirty = "false",
    onTurnDirty,
    mask,
    replacement,
    ...inputProps
  } = props;

  function handleBlur() {
    onTurnDirty(props.name);
  }

  if (mask) {
    return (
      <InputMask
        {...inputProps}
        mask={mask}
        replacement={replacement}
        data-invalid={invalid}
        data-dirty={dirty}
        onBlur={handleBlur}
      />
    );
  }

  return (
    <input
      {...inputProps}
      data-invalid={invalid}
      data-dirty={dirty}
      onBlur={handleBlur}
    />
  );
}